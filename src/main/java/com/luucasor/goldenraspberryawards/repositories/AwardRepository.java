package com.luucasor.goldenraspberryawards.repositories;

import com.luucasor.goldenraspberryawards.models.Award;
import com.luucasor.goldenraspberryawards.models.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardRepository extends CrudRepository<Award, Long> {
    public List<Movie> findWinnerMoviesByDateYear(int date);

    public List<Award> findAll();
}
