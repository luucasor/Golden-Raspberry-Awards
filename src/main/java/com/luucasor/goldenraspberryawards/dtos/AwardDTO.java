package com.luucasor.goldenraspberryawards.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AwardDTO implements Comparable<AwardDTO>{

    String producer;
    Integer interval;
    int previousWin;
    int followingWin;

    @Override
    public int compareTo(AwardDTO o) {
        return this.getInterval().compareTo(o.getInterval());
    }
}
