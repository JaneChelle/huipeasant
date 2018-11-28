package com.wlgzs.huipeasant.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author:胡亚星
 * @createTime 2018-05-12 11:30
 * @description:
 **/
@Configuration
public class FilterConfiguration {
    @Bean
    public FilterRegistrationBean filterDemoRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //注入过滤器
        registration.setFilter(new DemoFilter());
        //拦截规则
        registration.addUrlPatterns("/AdminDataController/*");
        registration.addUrlPatterns("/AdminVideoController/*");
        registration.addUrlPatterns("/adminUserController/*");
        //过滤器名称
        registration.setName("DemoFilter");
        //是否自动注册 false 取消Filter的自动注册
        registration.setEnabled(true);
        //过滤器顺序
        registration.setOrder(Integer.MAX_VALUE - 1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean filterLoginRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //注入过滤器
        registration.setFilter(new LoginFilter());
        //拦截规则
        registration.addUrlPatterns("/CollectionController/*");
        registration.addUrlPatterns("/UserManagementController/*");
        registration.addUrlPatterns("/user/tocomment");
        registration.addUrlPatterns("/user/morecomment/*");
        registration.addUrlPatterns("/user/morecomment/*");
        //过滤器名称
        registration.setName("LoginFilter");
        //是否自动注册 false 取消Filter的自动注册
        registration.setEnabled(true);
        //过滤器顺序
        registration.setOrder(Integer.MAX_VALUE);
        return registration;
    }

}