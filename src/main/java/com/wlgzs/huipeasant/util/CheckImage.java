package com.wlgzs.huipeasant.util;




import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;

public class CheckImage {
    public boolean verifyImage(String fileName){
        String reg="(?i).+?\\.(jpg|gif|bmp|png)";
        return fileName.matches(reg);
    }

    public boolean verifyImages(String[] fileNames){
        String reg="(?i).+?\\.(jpg|gif|bmp|png)";
        for(int i = 0;i<fileNames.length;i++){
            if(!fileNames[i].matches(reg)){
                return false;
            }
        }
        return true;
    }

    public boolean isVedioFile(String fileName){
        String reg="(?i).+?\\.(mp4|rmvb|flv|mpeg|avi)";
        return fileName.matches(reg);
    }
}
