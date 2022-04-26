package com.providence.gallery.controller;

import com.providence.gallery.database.entity.BaseResponseEntity;
import com.providence.gallery.database.entity.PhotoEntity;
import com.providence.gallery.logic.GetPhotosLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/photo")
public class HelloWorld {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GetPhotosLogic getPhotosLogic;

    @RequestMapping("/update")
    public void updateDBFromNas(String updateDir){
        logger.debug("controller:update");
        getPhotosLogic.updateDBFromNas(updateDir);
    }

    @RequestMapping("/random")
    public BaseResponseEntity randomPhotos(String randomSeed){
        logger.debug("controller:" + randomSeed);
        BaseResponseEntity response = new BaseResponseEntity();
        try{
            List<PhotoEntity> photos = getPhotosLogic.getAllPhotos();
            if(photos.size()!=0){
                response.setData(photos);
            }
            response.setResultCode("0000");
            response.setResultDes("success");
        }catch (Exception e){
            response.setResultCode("9999");
            response.setResultDes("failed");
        }
        return response;
    }
}
