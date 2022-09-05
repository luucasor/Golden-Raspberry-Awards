package com.luucasor.goldenraspberryawards.services;

import com.luucasor.goldenraspberryawards.dtos.MovieDTO;
import com.luucasor.goldenraspberryawards.models.Award;
import com.luucasor.goldenraspberryawards.models.Movie;
import com.luucasor.goldenraspberryawards.repositories.MovieRepository;
import com.luucasor.goldenraspberryawards.transformers.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public boolean createAwards(List<MovieDTO> moviesDTO){
        //TODO Separar listas por ano
        //TODO Inserir awards
        //TODO Associar nominees
        return false;
    }

    public boolean createMovies(List<MovieDTO> moviesDTO){
        MovieTransformer movieTransformer = new MovieTransformer(producerService, studioService, awardService);
        List<Movie> movies = (List<Movie>) movieRepository.saveAll(movieTransformer.dtosToEntities(moviesDTO));
        return movies != null && !movies.isEmpty();
    }
}
