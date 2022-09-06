package com.luucasor.goldenraspberryawards.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String title;

    @Column(name = "\"DATE_YEAR\"")
    int dateYear;

    @ManyToOne
    private Studio studio;

    @ManyToOne
    private Producer producer;
}