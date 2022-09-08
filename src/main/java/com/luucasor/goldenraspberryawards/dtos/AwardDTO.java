package com.luucasor.goldenraspberryawards.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AwardDTO {

    String producer;
    int interval;
    int previousWin;
    int followingWin;
}
