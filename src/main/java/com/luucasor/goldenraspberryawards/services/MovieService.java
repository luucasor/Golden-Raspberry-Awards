package com.luucasor.goldenraspberryawards.services;

import com.luucasor.goldenraspberryawards.dtos.MovieDTO;
import com.luucasor.goldenraspberryawards.models.Movie;
import com.luucasor.goldenraspberryawards.repositories.MovieRepository;
import com.luucasor.goldenraspberryawards.transformers.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    ProducerService producerService;
    @Autowired
    StudioService studioService;
    @Autowired
    AwardService awardService;
    public List<Movie> createMovies(List<MovieDTO> moviesDTO){
        List<Movie> movies = new ArrayList<>();
        MovieTransformer movieTransformer = new MovieTransformer(producerService, studioService, awardService);
        List<Movie> entities = movieTransformer.dtosToEntities(moviesDTO);
        for (Movie item: entities) {
            movies.add(movieRepository.save(item));
        }
        return movies;
    }

    public void createAwards(List<MovieDTO> moviesDTO) {
        HashMap<Integer, List<Movie>> moviesFilteredByYear = getMoviesByYear();
        Stream<Integer> keys = moviesFilteredByYear.keySet().stream().sorted();
        for (Integer key: keys.toList()) {
            List<Movie> movieList = moviesFilteredByYear.get(key);
            if(movieList != null){
                List<MovieDTO> winners = moviesDTO.stream().filter(m -> key.equals(m.getYear()) && m.isWinner()).toList();
                if(!winners.isEmpty()){
                    List<Movie> winnersEntities = new ArrayList<>();
                    for(MovieDTO item: winners){
                        Movie winner = movieRepository.findByDateYearAndTitle(key, item.getTitle());
                        winnersEntities.add(winner);
                    }

                    awardService.createAwardsByYear(key, winnersEntities, movieList);
                }
            }
        }
    }

    private HashMap<Integer, List<Movie>> getMoviesByYear(){
        List<Movie> movies = (List<Movie>) movieRepository.findAll();
        HashMap<Integer, List<Movie>> moviesFilteredByYear = new HashMap<>();

        movies.forEach(m ->{
            List<Movie> moviesYear = moviesFilteredByYear.get(m.getDateYear());
            if(moviesYear == null || moviesYear.isEmpty()){
                moviesYear = new ArrayList<>();
            }
            moviesYear.add(m);
            moviesFilteredByYear.put(m.getDateYear(), moviesYear);
        });
        return moviesFilteredByYear;
    }
}
