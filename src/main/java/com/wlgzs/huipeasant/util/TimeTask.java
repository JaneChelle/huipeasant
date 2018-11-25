package com.wlgzs.huipeasant.util;

import com.wlgzs.huipeasant.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 武凯焱
 * @date 2018/11/25 16:56
 * @Description:
 */
@Component
public class TimeTask {
    @Autowired
    DataService dataService;
    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void run(){
        dataService.index();
        dataService.question();
        dataService.information();
        dataService.indexRank();
    }
}
