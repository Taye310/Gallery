package com.providence.gallery.logic;

import com.providence.gallery.database.MainDAO;
import com.providence.gallery.database.entity.PhotoEntity;
import com.providence.gallery.utils.FtpUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GetPhotosLogic {

    @Autowired
    private MainDAO mainDAO;

    public List<PhotoEntity> getAllPhotos(){
        List<PhotoEntity> result = mainDAO.selectAllPhoto();
        return result;
    }

    public void updateDBFromNas(String updateDir) {
        FTPClient ftpClient;
        List<String> pictures = new ArrayList<>();
        try{
            ftpClient = FtpUtil.ftpConnection();
            ftpClient.enterLocalPassiveMode();
            ftpClient.setControlEncoding("utf-8");
            for (FTPFile file:ftpClient.listFiles(updateDir)) {
                pictures.add(file.getName());
                System.out.println(file.getName());
            }
            FtpUtil.close(ftpClient);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
