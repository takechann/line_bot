package com.linebot.pitcher;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="pitcher_player_table")
@Data
public class Pitcher {

    @Id
    @Column(name="name")
    private String name;

    @Column(name="pitching_count")
    private int pitching_count;

    @Column(name="winning")
    private int winning;

    @Column(name="defeat")
    private int defeat;

    @Column(name="save")
    private int save;

    @Column(name="hold")
    private int hold;

    @Column(name="hp")
    private int hp;

    @Column(name="complete_game")
    private int complete_game;

    @Column(name="shutout")
    private int shutout;

    @Column(name="no_four_balls")
    private int no_four_balls;

    @Column(name="win_rate")
    private double win_rate;

    @Column(name="batter")
    private int batter;

    @Column(name="pitching_times")
    private double pitching_times;

    @Column(name="hit")
    private int hit;

    @Column(name="home_run")
    private int home_run;

    @Column(name="four_balls")
    private int four_balls;

    @Column(name="intention_four_balls")
    private int intention_four_balls;

    @Column(name="dead_ball")
    private int dead_ball;

    @Column(name="strikeout")
    private int strikeout;

    @Column(name="wild_pitch")
    private int wild_pitch;

    @Column(name="boke")
    private int boke;

    @Column(name="loss")
    private int loss;

    @Column(name="earned_loss")
    private int earned_loss;

    @Column(name="era")
    private double era;

}
