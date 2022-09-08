package com.luucasor.goldenraspberryawards.services;

import com.luucasor.goldenraspberryawards.dtos.AwardDTO;
import com.luucasor.goldenraspberryawards.dtos.TimeGapDTO;
import com.luucasor.goldenraspberryawards.models.Award;
import com.luucasor.goldenraspberryawards.models.Movie;
import com.luucasor.goldenraspberryawards.models.Producer;
import com.luucasor.goldenraspberryawards.repositories.AwardRepository;
import com.luucasor.goldenraspberryawards.repositories.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProducerService {

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private AwardRepository awardRepository;

    public Producer findByName(String name){
        return producerRepository.findByName(name);
    }

    public TimeGapDTO getlongestAwardTimeGap() {
        List<Award> awards = awardRepository.findAll();

        HashMap<Producer, List<Award>> awardWinningProducers = new HashMap<>();
        awards.forEach(award -> {
            List<Movie> winnerMovies = award.getWinnerMovies();
            winnerMovies.forEach(movie -> {
                List<Producer> producers = movie.getProducers();
                producers.forEach(producer -> {
                    List<Award> aw = awardWinningProducers.get(producer);
                    if(aw == null){
                        aw = new ArrayList<>();
                    }
                    aw.add(award);
                    awardWinningProducers.put(producer, aw);
                });
            });
        });

        List<AwardDTO> awardsDTO = new ArrayList<>();
        awardWinningProducers.forEach((k,v) -> {
            if(v.size() > 1){
                AwardDTO awardDTO = new AwardDTO();
                awardDTO.setProducer(k.getName());
                awardDTO.setPreviousWin(Math.min(v.get(0).getDateYear(), v.get(1).getDateYear()));
                awardDTO.setFollowingWin(Math.max(v.get(0).getDateYear(), v.get(1).getDateYear()));
                awardDTO.setInterval(awardDTO.getFollowingWin()-awardDTO.getPreviousWin());
                awardsDTO.add(awardDTO);
            }
        });

        List<AwardDTO> list = awardsDTO.stream().sorted(Comparator.comparing(AwardDTO::getInterval)).collect(Collectors.toList());
        TimeGapDTO timeGapDTO = new TimeGapDTO();
        timeGapDTO.setMin(Arrays.asList(list.get(0), list.get(1)));
        timeGapDTO.setMax(Arrays.asList(list.get(2), list.get(3)));

        return timeGapDTO;
    }

    public Producer save(Producer producer) {
        return producerRepository.save(producer);
    }
}
