package com.wlgzs.huipeasant.service.impl;

import com.wlgzs.huipeasant.dao.DataDao;
import com.wlgzs.huipeasant.entity.Data;
import com.wlgzs.huipeasant.service.DataService;
import com.wlgzs.huipeasant.util.IoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DataServiceImpl implements DataService {
    @Autowired
    DataDao dataRepository;

    public boolean addData(Data data, MultipartFile multipartFile ,int dataLevle) {
        IoUtil ioUtil = new IoUtil();
        if (data.getContents() != null && data.getModuleName() != null && data.getModuleId() != 0 && data.getContentsTitle() != null) {
            if (dataLevle==2){
             data.setModuleId(1);
            }
            dataRepository.save(data);
            String dataimgUrl = "/upload/dataimg/"+data.getDataId()+"/"+ multipartFile.getOriginalFilename();
            ioUtil.saveFile(multipartFile,dataimgUrl);
            data.setPictureAddress(dataimgUrl);
            dataRepository.save(data);
            return true;
        }
        return false;
    }
}
