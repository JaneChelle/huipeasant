package com.wlgzs.huipeasant.service.impl;

import com.wlgzs.huipeasant.dao.CommentDao;
import com.wlgzs.huipeasant.dao.DataDao;
import com.wlgzs.huipeasant.dao.MouduleDao;
import com.wlgzs.huipeasant.entity.Comment;
import com.wlgzs.huipeasant.entity.Data;
import com.wlgzs.huipeasant.entity.Module;
import com.wlgzs.huipeasant.entity.User;
import com.wlgzs.huipeasant.service.DataService;
import com.wlgzs.huipeasant.util.IoUtil;
import org.hibernate.annotations.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Service
public class DataServiceImpl implements DataService {
    @Autowired
    DataDao dataRepository;
    @Autowired
    MouduleDao mouduleDao;
    @Autowired
    CommentDao commentDao;
    @Source
    IoUtil ioUtil;
    @Autowired
    HttpSession session;

@Override
    public boolean
addData(Data data, MultipartFile multipartFile ,int dataLevle) throws IOException {
        IoUtil ioUtil = new IoUtil();
        if (data.getContentsTitle() != null) {
            if (dataLevle==2){              //判断是否是提问问题
             data.setModuleId(1);
                User user = (User)(session.getAttribute("user"));
                data.setUserIcon(user.getHeadPortrait());
                data.setUserName(user.getNickName());
            }
            if (dataLevle==3){
                data.setModuleId(9);
            }
            if (data.getPictureAddress()!=null && !data.getPictureAddress().equals("") ){
                 ioUtil.deleteFile("."+data.getPictureAddress());
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
    public List<Data> toindex(long moudleId){   //进入主页添加得到数据
        List<Data> list = new ArrayList<Data>();
        List<Data> arrayList = new ArrayList<Data>();
        System.out.println("aaaaaa"+moudleId);
        list=dataRepository.findByModuleId(moudleId);
        if (list.size()>=16){
            arrayList=list.subList(0,16);
            for (Data data:list.subList(7,12)) {
                data.setIdentity(2);
            }
            list.get(7).setIdentity(4);
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
    @Override
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
    @Override
    public List<Data> question(){
    List<Data> dataList = new ArrayList<Data>();
    dataList =dataRepository.findByModuleLevelOrderByUploadTimeDesc(2);
    if (dataList!=null && dataList.size()>=10){
        return dataList.subList(0,9);
    }
    return null;
    }
    @Override
    public List<Data> information(){
    List<Data> dataList = dataRepository.findByModuleLevelOrderByUploadTimeDesc(3);
    if (dataList.size()>=4){
        return dataList.subList(0,3);
    }
    return null;
    }
    @Override
    public List<Data> indexRank(){
    List<Data> dataList = dataRepository.findByModuleLevelOrderByHitsDesc(1);
        System.out.println("fikosepkifgtrepsdotg"+dataList.toString());
    return dataList.subList(0,10);
    }
    @Override
    public boolean jundegeView(long dataId){
    Data data = dataRepository.findById(dataId).get();
    if (data.getModuleLevel()==2){
        return false;
    }else {
        return true;
    }
    }
    @Override
    public Data dataView(long dataId){
    Data data = dataRepository.findById(dataId).get();
    if (data!=null){
        return data;
    }else {
        return null;
    }
    }
    public List<Data> recommend(long dataId){
    Data data = dataRepository.findById(dataId).get();
        Pageable pageable = new PageRequest(0,4);
        Specification<Data> specification = new Specification<Data>() {
            @Override
            public Predicate toPredicate(Root<Data> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                Path<Long> moudleId =root.get("moduleId");
                Predicate _moudleId =criteriaBuilder.equal(moudleId,data.getModuleId());
                Path<Long> _dataId = root.get("dataId");
                Predicate _Id = criteriaBuilder.notEqual(_dataId,data.getDataId());
                predicates.add(_moudleId);
                predicates.add(_Id);
                return criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
            }
        };
        Page<Data> dataPage = dataRepository.findAll(specification,pageable);
        return dataPage.getContent();

    }
    @Override
    public void moudelDatas(Model model,long moudelId, long page){
    if (page<=0){
        page=0;
    }
    Pageable pageable = new PageRequest((int) (page-1),12);
    Specification<Data> specification =new Specification<Data>() {
        @Override
        public Predicate toPredicate(Root<Data> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
           Path moduleId = root.get("moduleId");
           Predicate _module = criteriaBuilder.equal(moduleId,moudelId);
           return criteriaBuilder.and(_module);
        }
    };
    Page<Data> dataPage = dataRepository.findAll(specification,pageable);
     model.addAttribute("datas",dataPage.getContent()) ;
     model.addAttribute("pages",dataPage.getTotalPages());
     model.addAttribute("page",page);
     }
    @Override
    public List<Data>  relevantIssues() {                 //文章页面显示正在问的问题
    List<Data> dataList = new ArrayList<Data>();
    Pageable pageable = new PageRequest(0,4);
    Page<Data> dataPage = dataRepository.findAll(pageable);
    return dataPage.getContent();
    }
    @Override
    public Data textView(long dataId) {  // 获取指定文章
    Data data = dataRepository.findById(dataId).get();
    return data;
    }
    @Override
    public List<String> paragraphList(String text) {      //给文章分段
        List<String> list = new ArrayList<String>();
        Scanner scanner = new Scanner(text);
        while (scanner.hasNext()) {
            String world = scanner.next();
            list.add(world);
        }
        return list;
    }

    @Override
    public List<Data> userGetquestion(long userId){    //用户在个人中心获取自己问的问题
    List<Data> dataList = new ArrayList<Data>();
    dataList = dataRepository.findByUserIdAndModuleLevel(userId,2);
    return  dataList;
    }
    public List<String> getKeyWord(String keyWord) {  //进行模糊搜索
        List<Data> dataList =dataRepository.findByContentsTitleContaining(keyWord);
        if (dataList == null) {
            return null;
        }
        List<String> keyWordList = new ArrayList<String>();
        if (dataList.size() <= 10) {
            for (Data data : dataList
                    ) {
                keyWordList.add(data.getContentsTitle());
            }
        } else {
            Data data = new Data();
            for (int i = 0; i < 10; i++) {
                data = dataList.get(i);
                keyWordList.add(data.getContentsTitle());
            }
        }
        return keyWordList;
    }
    public List<Data> searchData(String dataName){
    List<Data> dataList = new ArrayList<Data>();
    dataList = dataRepository.findByContentsTitle(dataName);
    return dataList;
    }
    public void ipgetDatas (int moduleLevel,int page,Model model){    //根据模块的等级来获取相应数据
        Sort sort = new Sort(Sort.Direction.DESC, "hits");
    Pageable pageable = new PageRequest(page-1,10, sort);
    Specification<Data> specification = new Specification<Data>() {
        @Override
        public Predicate toPredicate(Root<Data> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            Path leLevel = root.get("moduleLevel");
            Predicate _leLevel = criteriaBuilder.equal(leLevel,moduleLevel);
            return criteriaBuilder.and(_leLevel);
        }
    };
    Page<Data> dataPage = dataRepository.findAll(specification,pageable);
        List<Data> dataList = dataPage.getContent();
        for (int i=0;i<dataList.size();i++){
            dataList.get(i).setIdentity(i*((page-1)*10)+1);
        }
        model.addAttribute("datas",dataList) ;
        model.addAttribute("pages",dataPage.getTotalPages());
        model.addAttribute("page",page);
    }
    public Map<Data,List<Comment>> ipQuestion(int moduleLevel,int page,Model model){  //在手机端主页展示的问题
            Map<Data,List<Comment>> commentListMap = new HashMap<>();
        List<Data> dataList = new ArrayList<>();
         Pageable pageable = new PageRequest(page-1,10);
         Specification specification  = new Specification() {
             @Override
             public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                 Path moudelevle = root.get("moduleLevel");
                 Predicate predicate = criteriaBuilder.equal(moudelevle,moduleLevel);
                 return criteriaBuilder.and(predicate);
             }
         };
         Page<Data> dataPage = dataRepository.findAll(specification,pageable);
         dataList = dataPage.getContent();
         for (int i=0;i<dataList.size();i++){
             Data data = dataList.get(i);
              commentListMap.put(data,commentDao.findByDataId(data.getDataId()));
         }
         return commentListMap;
    }
    public void delete(long dataId){
     dataRepository.deleteById(dataId);
    }
    public void deletedatas(long[] dataIds){
      for (int i=0; i<dataIds.length;i++){
          dataRepository.deleteById(dataIds[i]);
      }
    }
}