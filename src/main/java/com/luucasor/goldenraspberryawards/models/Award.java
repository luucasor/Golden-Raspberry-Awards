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

    @Column(name = "\"DATE_YEAR\"")
    int dateYear;
}
