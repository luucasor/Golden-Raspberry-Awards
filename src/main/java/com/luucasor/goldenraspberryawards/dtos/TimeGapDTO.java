package com.luucasor.goldenraspberryawards.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TimeGapDTO {

    List<AwardDTO> min = new ArrayList<>(2);
    List<AwardDTO> max = new ArrayList<>(2);
}
