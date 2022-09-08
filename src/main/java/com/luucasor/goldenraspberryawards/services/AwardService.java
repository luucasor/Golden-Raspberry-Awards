package com.luucasor.goldenraspberryawards.services;

import com.luucasor.goldenraspberryawards.models.Award;
import com.luucasor.goldenraspberryawards.models.Movie;
import com.luucasor.goldenraspberryawards.repositories.AwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AwardService {

    @Autowired
    AwardRepository awardRepository;
    public String isWinner(Movie movie) {
        String winner = "no";
        List<Movie>  winnerMovies = awardRepository.findWinnerMoviesByDateYear(movie.getDateYear());
        Movie filteredMovie = winnerMovies.stream().filter(m -> movie.getTitle().equals(m.getTitle())).findFirst().orElse(null);
        if(filteredMovie != null){
            winner = "yes";
        }
        return winner;
    }

    public void createAwardsByYear(Integer key, List<Movie> winnerMovies, List<Movie> movies) {
        Award award = new Award();
        award.setDateYear(key);
        award.setWinnerMovies(winnerMovies);
        award.setNominees(movies);
        awardRepository.save(award);
    }
}
