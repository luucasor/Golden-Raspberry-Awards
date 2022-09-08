package com.luucasor.goldenraspberryawards.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String title;

    int dateYear;

    @ManyToOne
    private Studio studio;

    @ManyToMany
    private List<Producer> producers;
}