package com.providence.gallery.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FtpUtil {

    private static final Logger logger = LoggerFactory.getLogger(FtpUtil.class);

    //账号
    private static String username = ResourceUtil.getValue("config.properties","ftp.username");
    //密码
    private static String password = ResourceUtil.getValue("config.properties","ftp.password");
    //地址
    private static String ip = ResourceUtil.getValue("config.properties","ftp.url");
    //端口号
    private static String port = ResourceUtil.getValue("config.properties","ftp.port");

    /**
     * ftp链接
     * @return
     * @throws IOException
     */
    public static FTPClient ftpConnection() throws IOException {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip, Integer.parseInt(port));
            ftpClient.login(username, password);
            int replyCode = ftpClient.getReplyCode(); //是否成功登录服务器
            if(!FTPReply.isPositiveCompletion(replyCode)) {
                ftpClient.disconnect();
                logger.error("--ftp连接失败--");
                System.exit(1);
            }
            ftpClient.enterLocalPassiveMode();//这句最好加告诉对面服务器开一个端口
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftpClient;
    }

    /**
     * 下载方法
     * @param ftpClient FTPClient对象
     * @param newFileName 新文件名
     * @param fileName 原文件名
     * @param downUrl  下载路径
     * @return
     * @throws IOException
     */
    public static boolean downFile(FTPClient ftpClient, String newFileName, String fileName, String downUrl) throws IOException {
        boolean isTrue = false;
        OutputStream os=null;
        File localFile = new File(downUrl + "/" + newFileName);
        os = new FileOutputStream(localFile);
        isTrue = ftpClient.retrieveFile(new String(fileName.getBytes(),"ISO-8859-1"), os);
        os.close();
        return isTrue;
    }

    /**
     * 断开FTP连接
     * @param ftpClient  初始化的对象
     * @throws IOException
     */
    public static void close(FTPClient ftpClient) throws IOException{
        if(ftpClient!=null && ftpClient.isConnected()){
            ftpClient.logout();
            ftpClient.disconnect();
        }
    }
}