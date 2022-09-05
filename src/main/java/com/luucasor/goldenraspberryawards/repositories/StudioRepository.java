package com.luucasor.goldenraspberryawards.repositories;

import com.luucasor.goldenraspberryawards.models.Studio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepository extends CrudRepository<Studio, Long> {
    Studio findByName(String name);
}
