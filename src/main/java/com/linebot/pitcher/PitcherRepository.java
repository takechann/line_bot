package com.linebot.pitcher;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PitcherRepository extends JpaRepository<Pitcher, Integer> {
    List<Pitcher> findByNameContaining(String name);
}