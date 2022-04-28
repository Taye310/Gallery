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

    public void updateDBFromNas(String updateDir) throws Exception {
        if(!updateDir.endsWith("/")){
            throw new Exception("path must ends with '/'");
        }
        FTPClient ftpClient;
        List<String> pictures = new ArrayList<>();
        try{
            ftpClient = FtpUtil.ftpConnection();
            ftpClient.enterLocalPassiveMode();
            ftpClient.setControlEncoding("utf-8");
            pictures = updateDBFromNasHelper(ftpClient, updateDir, pictures);
            FtpUtil.close(ftpClient);
        } catch (IOException e){
            e.printStackTrace();
        }

        for (String pic : pictures) {
            PhotoEntity tmp = new PhotoEntity();
            tmp.setLink(pic);
            mainDAO.insertPhoto(tmp);
        }
    }

    private List<String> updateDBFromNasHelper(FTPClient ftpClient, String dir, List<String> pictures) throws IOException {
        for (FTPFile file:ftpClient.listFiles(dir)) {
            if(".".equals(file.getName())) continue;
            if(file.isDirectory()){
                pictures = updateDBFromNasHelper(ftpClient, dir+"/"+file.getName()+"/", pictures);
            }else{
                pictures.add(dir + file.getName());
            }
        }
        return pictures;
    }
}
