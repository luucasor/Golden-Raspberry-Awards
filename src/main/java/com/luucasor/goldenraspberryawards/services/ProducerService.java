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

    public TimeGapDTO getMinAndMaxAwardTimeGap() {
        HashMap<Producer, List<Award>> listWinningProducers = getListWinningProducers();
        List<AwardDTO> list = sortByInterval(listWinningProducers);

        return new TimeGapDTO(Arrays.asList(list.get(0)), Arrays.asList(list.get(list.size()-1)));
    }

    private static List<AwardDTO> sortByInterval(HashMap<Producer, List<Award>> awardWinningProducers) {
        List<AwardDTO> awardsDTO = new ArrayList<>();
        awardWinningProducers.forEach((k, v) -> {
            if(v.size() > 1){
                AwardDTO awardDTO = new AwardDTO();
                awardDTO.setProducer(k.getName());
                for(int i = 0; i < v.size(); i++){
                    if(i+1 >= v.size()){
                        break;
                    }
                    int max = Math.max(v.get(i).getDateYear(), v.get(i+1).getDateYear());
                    int min = Math.min(v.get(i).getDateYear(), v.get(i+1).getDateYear());
                    awardDTO.setPreviousWin(min);
                    awardDTO.setFollowingWin(max);
                    awardDTO.setInterval(max-min);

                    boolean exists = awardsDTO.stream().anyMatch(a -> awardDTO.getProducer().equals(a.getProducer()) &&
                                                                                                    awardDTO.getInterval() == a.getInterval() &&
                                                                                                    awardDTO.getPreviousWin() == a.getPreviousWin() &&
                                                                                                    awardDTO.getFollowingWin() == a.getFollowingWin()
                    );
                    if(!exists){
                        awardsDTO.add(awardDTO);
                    }
                }
            }
        });
        return awardsDTO.stream().sorted(Comparator.comparing(AwardDTO::getInterval)).collect(Collectors.toList());
    }

    private HashMap<Producer, List<Award>> getListWinningProducers() {
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
        return awardWinningProducers;
    }

    public Producer save(Producer producer) {
        return producerRepository.save(producer);
    }
}
