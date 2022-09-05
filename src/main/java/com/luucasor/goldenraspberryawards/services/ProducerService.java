package com.luucasor.goldenraspberryawards.services;

import com.luucasor.goldenraspberryawards.dtos.TimeGapDTO;
import com.luucasor.goldenraspberryawards.models.Producer;
import com.luucasor.goldenraspberryawards.repositories.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Autowired
    private ProducerRepository producerRepository;

    public Producer findByName(String name){
        return producerRepository.findByName(name);
    }

    public TimeGapDTO getlongestAwardTimeGap() {
        return null;
    }

    public void save(Producer producer) {
        producerRepository.save(producer);
    }
}
