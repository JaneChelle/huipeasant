package com.wlgzs.huipeasant.service.impl;

import com.wlgzs.huipeasant.dao.DataDao;
import com.wlgzs.huipeasant.dao.MouduleDao;
import com.wlgzs.huipeasant.entity.Data;
import com.wlgzs.huipeasant.entity.Module;
import com.wlgzs.huipeasant.service.DataService;
import com.wlgzs.huipeasant.util.IoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class DataServiceImpl implements DataService {
    @Autowired
    DataDao dataRepository;
    @Autowired
    MouduleDao mouduleDao;

@Override
    public boolean addData(Data data, MultipartFile multipartFile ,int dataLevle) {
        IoUtil ioUtil = new IoUtil();
        if (data.getContentsTitle() != null) {
            if (dataLevle==2){              //判断是否是提问问题
             data.setModuleId(1);
            }
            if (dataLevle==3){
                data.setModuleId(9);
            }
            Module module = mouduleDao.findById(data.getModuleId()).get();
            Date date = new Date();
            data.setModuleName(module.getModuleName());
            data.setUploadTime(date);
            data.setModuleLevel(module.getModuleLevel());
            dataRepository.save(data);
            if (!(multipartFile==null)) {
                String dataimgUrl = "/upload/dataimg/" + data.getDataId() + "/" + multipartFile.getOriginalFilename();
                ioUtil.saveFile(multipartFile, dataimgUrl);
                data.setPictureAddress(dataimgUrl);
                dataRepository.save(data);
            }
            return true;
        }
        return false;
    }
    @Override
    public List<Data> toindex(long moudleId){
        List<Data> list = new ArrayList<Data>();
        List<Data> arrayList = new ArrayList<Data>();
        System.out.println("aaaaaa"+moudleId);
        list=dataRepository.findByModuleId(moudleId);
        System.out.println("aaaa"+list.toString()+"size"+list.size());
        if (list.size()>=16){
            System.out.println("asklhjdfuaiosdfhdsuhfdshfgdshjgjdsfhg");
            arrayList=list.subList(0,16);
            System.out.println("sdl;jgkmfdfklgjfkdlhjklfgjhmkfgjmghj"+list.size());
            for (Data data:list.subList(7,12)) {
                data.setIdentity(2);
            }
            for (Data data: arrayList
                 ) {
                if (data.getPictureAddress()!=null){
                    data.setIdentity(1);
                    break;
                }
            }
            return arrayList;
        }
        return null;
    }

    public Map<Module,List<Data>> index (){
    Map<Module,List<Data>> dataListMap = new HashMap<>();
    List<Module> list = new ArrayList<Module>();
        list = mouduleDao.findByModuleLevel(1);
        System.out.println(list.toString());
        for (Module module : list
             ) {
            List<Data> dataList = new ArrayList<Data>();
            dataList = toindex(module.getMouldeId());
            if (dataList!=null){
                dataListMap.put(module,dataList);
            }
        }
        return dataListMap;
    }
    public List<Data> question(){
    List<Data> dataList = new ArrayList<Data>();
    dataList =dataRepository.findBymoduleLevelOrderByUploadTimeDesc(2);
    if (dataList!=null && dataList.size()>=10){
        return dataList.subList(0,9);
    }
    return null;
    }
    public List<Data> information(){
    List<Data> dataList = dataRepository.findBymoduleLevelOrderByUploadTimeDesc(2);
    if (dataList.size()>=4){
        return dataList.subList(0,3);
    }
    return null;
    }
    public List<Data> indexRank(){
    List<Data> dataList = dataRepository.findBymoduleLevelOrderByHitsDesc(1);
    return dataList.subList(0,10);
    }
}
