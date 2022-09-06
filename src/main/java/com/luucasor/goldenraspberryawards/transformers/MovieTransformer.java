package com.luucasor.goldenraspberryawards.transformers;

import com.luucasor.goldenraspberryawards.dtos.MovieDTO;
import com.luucasor.goldenraspberryawards.models.Movie;
import com.luucasor.goldenraspberryawards.models.Producer;
import com.luucasor.goldenraspberryawards.models.Studio;
import com.luucasor.goldenraspberryawards.services.AwardService;
import com.luucasor.goldenraspberryawards.services.ProducerService;
import com.luucasor.goldenraspberryawards.services.StudioService;

import java.util.ArrayList;
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
        entity.setTitle(dto.getTitle());
        entity.setDateYear(dto.getYear());
        entity.setProducer(getProducer(dto));
        entity.setStudio(getStudio(dto));
        return entity;
    }

    private Producer getProducer(MovieDTO dto){
        Producer producer = producerService.findByName(dto.getProducers());
        if(producer == null){
            producer = new Producer(dto.getProducers());
            producerService.save(producer);
        }
        return producer;
    }

    private Studio getStudio(MovieDTO dto) {
        Studio studio = studioService.findByName(dto.getStudios());
        if(studio == null){
            studio = new Studio(dto.getStudios());
            studioService.save(studio);
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
