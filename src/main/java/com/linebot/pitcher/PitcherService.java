package com.linebot.pitcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Service
@Transactional
public class PitcherService {

	@Autowired
    PitcherRepository repository;

    Logger logger = Logger.getLogger(PitcherService.class.getName());

	public List<Pitcher> selectAll() {
        return repository.findAll();
        // return repository.findAll(new Sort(Sort.Direction.ASC, "id"));
    }

    public List<Pitcher> selectName(String name) {
        logger.info("--> select name is " + name);
        return repository.findByNameContaining(name);
        // return repository.findAll(new Sort(Sort.Direction.ASC, "id"));
    }

}