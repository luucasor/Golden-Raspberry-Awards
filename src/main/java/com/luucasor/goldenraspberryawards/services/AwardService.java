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
        String winner = "no";
        Award award = awardRepository.findWinnerByDateYear(movie.getDateYear());
        if(movie.getTitle().equals(award.getWinner().getTitle())){
            winner = "yes";
        }
        return winner;
    }

    public void createAwardsByYear(Integer key, Movie winner) {
        Award award = new Award();
        award.setDateYear(key);
        award.setWinner(winner);
        awardRepository.save(award);
    }
}
