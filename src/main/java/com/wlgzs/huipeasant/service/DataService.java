package com.wlgzs.huipeasant.service;

import com.wlgzs.huipeasant.entity.Data;
import com.wlgzs.huipeasant.entity.Module;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface DataService {
   boolean addData(Data data, MultipartFile multipartFile,int datalevel);
   List<Data> toindex(long moudleId);
   Map<Module,List<Data>> index ();
   List<Data> question();
   List<Data> information();
   List<Data> indexRank();
}
