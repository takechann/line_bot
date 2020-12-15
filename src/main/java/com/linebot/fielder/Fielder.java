package com.linebot.fielder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="fielder_player_table")
@Data
public class Fielder {

    @Id
    @Column(name="name")
    private String name;

    @Column(name="game")
    private int game;

    @Column(name="pa")
    private int pa;

    @Column(name="at_bats")
    private int at_bats;

    @Column(name="score")
    private int score;

    @Column(name="hit")
    private int hit;

    @Column(name="two_baser")
    private int two_baser;

    @Column(name="three_baser")
    private int three_baser;

    @Column(name="home_run")
    private int home_run;

    @Column(name="base_hit")
    private int base_hit;

    @Column(name="rbi")
    private int rbi;

    @Column(name="steal")
    private int steal;

    @Column(name="caught_stealing")
    private int caught_stealing;

    @Column(name="sacrifice_hit")
    private int sacrifice_hit;

    @Column(name="sacrifice_fly")
    private int sacrifice_fly;

    @Column(name="four_balls")
    private int four_balls;

    @Column(name="intention_four_balls")
    private int intention_four_balls;

    @Column(name="dead_ball")
    private int dead_ball;

    @Column(name="strikeout")
    private int strikeout;

    @Column(name="double_play")
    private int double_play;

    @Column(name="batting_average")
    private double batting_average;

    @Column(name="slugging_percentage")
    private double slugging_percentage;

    @Column(name="on_base_percentage")
    private double on_base_percentage;

    @Column(name="ops")
    private double ops;

}
