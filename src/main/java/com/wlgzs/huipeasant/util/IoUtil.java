package com.wlgzs.huipeasant.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class IoUtil {
    public boolean saveFile(MultipartFile file,String filePath){
        try {
            if (!file.isEmpty()){
                File saveFile = new File("."+filePath);
                if (!saveFile.getParentFile().exists()){
                    saveFile.getParentFile().mkdirs();
                }
                FileOutputStream outputStream =new FileOutputStream(saveFile);
                BufferedOutputStream out = new BufferedOutputStream(outputStream);
                out.write(file.getBytes());
                out.flush();
                out.close();
                outputStream.close();
                return true;
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
