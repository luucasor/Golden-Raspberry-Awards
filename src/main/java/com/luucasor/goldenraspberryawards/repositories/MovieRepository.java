package com.luucasor.goldenraspberryawards.repositories;

import com.luucasor.goldenraspberryawards.models.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {
    Movie findByDateYearAndTitle(Integer dateYear, String title);
}
