package com.wlgzs.huipeasant.service;

import com.wlgzs.huipeasant.entity.Comment;
import com.wlgzs.huipeasant.entity.Data;
import com.wlgzs.huipeasant.entity.Module;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface DataService {
    boolean addData(Data data, MultipartFile multipartFile, int datalevel) throws IOException;

    List<Data> toindex(long moudleId);   //加载跳到主页的数据

    Map<Module, List<Data>> index();

    List<Data> question();

    List<Data> information();

    List<Data> indexRank();

    boolean jundegeView(long dataId);

    Data dataView(long dataId);

    void moudelDatas(Model model, long moudelId, long page); //一个模块的所有数据

    Data textView(long dataId);

    List<Data> relevantIssues();

    List<Data> recommend(long dataId);

    List<Data> userGetquestion(long userId); //获取每个问题答案的集合

    List<String> getKeyWord(String keyWord);

    List<Data> searchData(String dataName);

    void ipgetDatas(int moduleLevel, int page, Model model);

    Map<Data, List<Comment>> ipQuestion(int moduleLevel, int page, Model model);

    void delete(long dataId);

    void deletedatas(long[] dataIds); //批量删除数据

    List<String> paragraphList(String text);      //给文章分段
}
