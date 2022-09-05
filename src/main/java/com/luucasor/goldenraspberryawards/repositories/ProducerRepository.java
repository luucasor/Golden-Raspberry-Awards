package com.luucasor.goldenraspberryawards.repositories;

import com.luucasor.goldenraspberryawards.models.Producer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends CrudRepository<Producer, Long> {

    Producer findByName(String name);
}
