package com.linebot.fielder;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FielderRepository extends JpaRepository<Fielder, Integer> {
    List<Fielder> findByNameContaining(String name);
}