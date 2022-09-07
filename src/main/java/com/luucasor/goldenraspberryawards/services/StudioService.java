package com.luucasor.goldenraspberryawards.services;

import com.luucasor.goldenraspberryawards.models.Studio;
import com.luucasor.goldenraspberryawards.repositories.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudioService {

    @Autowired
    private StudioRepository studioRepository;

    public Studio findByName(String name){
        return studioRepository.findByName(name);
    }

    public Studio save(Studio studio) {
        return studioRepository.save(studio);
    }
}
