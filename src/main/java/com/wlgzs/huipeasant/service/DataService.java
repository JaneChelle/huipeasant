package com.wlgzs.huipeasant.service;

import com.wlgzs.huipeasant.entity.Data;
import org.springframework.web.multipart.MultipartFile;

public interface DataService {
    public boolean addData(Data data, MultipartFile multipartFile,int datalevel);


}
