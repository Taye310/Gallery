package com.providence.gallery.logic;

import com.providence.gallery.database.MainDAO;
import com.providence.gallery.database.entity.PhotoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPhotosLogic {

    @Autowired
    private MainDAO mainDAO;

    public List<PhotoEntity> GetAllPhotos(){
        List<PhotoEntity> result = mainDAO.selectAllPhoto();
        return result;
    }
}
