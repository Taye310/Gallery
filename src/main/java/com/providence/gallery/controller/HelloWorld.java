package com.providence.gallery.controller;

import com.providence.gallery.database.entity.BaseResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/photo")
public class HelloWorld {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/random")
    public BaseResponseEntity randomPhotos(String randomSeed){
        logger.debug("controller:" + randomSeed);

        return null;
    }
}
