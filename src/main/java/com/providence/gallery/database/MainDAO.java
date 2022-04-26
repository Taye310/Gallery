package com.providence.gallery.database;

import com.providence.gallery.database.entity.PhotoEntity;

import java.util.List;

public interface MainDAO {
    public List<PhotoEntity> selectAllPhoto();
}
