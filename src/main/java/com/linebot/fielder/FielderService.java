package com.linebot.fielder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Service
@Transactional
public class FielderService {

	@Autowired
    FielderRepository repository;

    Logger logger = Logger.getLogger(FielderService.class.getName());

	public List<Fielder> selectAll() {
        return repository.findAll();
        // return repository.findAll(new Sort(Sort.Direction.ASC, "id"));
    }

    public List<Fielder> selectName(String name) {
        logger.info("--> select name is " + name);
        return repository.findByNameContaining(name);
        // return repository.findAll(new Sort(Sort.Direction.ASC, "id"));
    }

}