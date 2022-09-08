package com.luucasor.goldenraspberryawards.transformers;

import com.luucasor.goldenraspberryawards.dtos.MovieDTO;
import com.luucasor.goldenraspberryawards.models.Movie;
import com.luucasor.goldenraspberryawards.models.Producer;
import com.luucasor.goldenraspberryawards.models.Studio;
import com.luucasor.goldenraspberryawards.services.AwardService;
import com.luucasor.goldenraspberryawards.services.ProducerService;
import com.luucasor.goldenraspberryawards.services.StudioService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieTransformer {

    ProducerService producerService;
    StudioService studioService;
    AwardService awardService;

    public MovieTransformer(ProducerService producerService, StudioService studioService, AwardService awardService){
        this.producerService = producerService;
        this.studioService = studioService;
        this.awardService = awardService;
    }

    public Movie dtoToEntity(MovieDTO dto){
        Movie entity = new Movie();
        entity.setTitle(dto.getTitle().trim());
        entity.setDateYear(dto.getYear());
        entity.setProducers(getProducers(dto));
        entity.setStudio(getStudio(dto));
        return entity;
    }

    private List<Producer> getProducers(MovieDTO dto){
        List<Producer> producers = splitProducers(dto.getProducers().trim());
        producers = createNewProducerIfNotExists(producers);
        return producers;
    }

    private List<Producer> createNewProducerIfNotExists(List<Producer> producers) {
        List<Producer> savedProducers = new ArrayList<>();
        for (Producer item: producers) {
            Producer producer = producerService.findByName(item.getName());
            if(producer == null){
                savedProducers.add(producerService.save(item));
            } else {
                savedProducers.add(producer);
            }
        }
        return savedProducers;
    }

    private List<Producer> splitProducers(String stringProducers) {
        List<Producer> producers = new ArrayList<>();
        if(stringProducers.contains(" and ")){
            stringProducers = stringProducers.replace(", and ", ",").replace(" and ", ", ");
            List<String> commaParts = Arrays.stream(stringProducers.split(",")).toList();
            commaParts.forEach(item -> {
                producers.add(new Producer(item.trim()));
            });
        } else {
            producers.add(new Producer(stringProducers));
        }
        return producers;
    }

    private Studio getStudio(MovieDTO dto) {
        Studio studio = studioService.findByName(dto.getStudios().trim());
        if(studio == null){
            studio = new Studio(dto.getStudios().trim());
            studio = studioService.save(studio);
        }
        return studio;
    }

    public List<Movie> dtosToEntities(List<MovieDTO> moviesDTO) {
        List<Movie> movies = null;
        if(moviesDTO != null && !moviesDTO.isEmpty()){
            movies = new ArrayList<>();
            for (MovieDTO dto: moviesDTO) {
                movies.add(dtoToEntity(dto));
            }
        }
        return movies;
    }

    public MovieDTO entityToDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setYear(movie.getDateYear());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setStudios(movie.getStudio().getName());
        movieDTO.setWinner(awardService.isWinner(movie));
        return movieDTO;
    }
}
