package com.luucasor.goldenraspberryawards.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    List<Movie> nominees;

    @OneToOne
    Movie winner;

    @Column(name = "\"YEAR\"")
    int year;
    @Column(name = "\"INTERVAL\"")
    int interval;
    int previousWin;
    int followingWin;

    public void setNominess(List<Movie> nominees){
        this.nominees.addAll(nominees);
    }
}
