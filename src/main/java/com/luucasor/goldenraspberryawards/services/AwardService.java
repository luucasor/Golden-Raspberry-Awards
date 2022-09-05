package com.luucasor.goldenraspberryawards.services;

import com.luucasor.goldenraspberryawards.models.Award;
import com.luucasor.goldenraspberryawards.models.Movie;
import com.luucasor.goldenraspberryawards.repositories.AwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AwardService {

    @Autowired
    AwardRepository awardRepository;
    public String isWinner(Movie movie) {
        String winner = "";
        Award award = awardRepository.findWinnerByYear(movie.getYear());
        if(movie.getTitle().equals(award.getWinner().getTitle())){
            winner = "yes";
        }
        return winner;
    }
}
