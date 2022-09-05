package com.luucasor.goldenraspberryawards;

import com.luucasor.goldenraspberryawards.dtos.MovieDTO;
import com.luucasor.goldenraspberryawards.readers.CSVReader;
import com.luucasor.goldenraspberryawards.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    MovieService movieService;

    @Override
    public void run(String... args) throws Exception {
        List<MovieDTO> beans = (List<MovieDTO>) new CSVReader().getValues("movielist (5).csv", ';');
        movieService.createMovies(beans);
    }
}
