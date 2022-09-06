package com.luucasor.goldenraspberryawards.dtos;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDTO {

    @CsvBindByName
    private int year;
    @CsvBindByName
    private String title;
    @CsvBindByName
    private String studios;
    @CsvBindByName
    private String producers;
    @CsvBindByName
    private String winner;

    public boolean isWinner(){
        return "yes".equals(this.winner);
    }
}
