package com.dadaibiao.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xuedong Li
 * @version V1.0
 * @Package com.dadaibiao.shiro.config
 * @date 2020/8/13 18:14
 */
@Configuration
public class ShiroConfig {
    //ShiroFilterFactoryBean  3
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(securityManager);


        //添加shiro的内置过滤器--------------------------
        /**
         * @description
         * onon:无需认证就可以访问
         * authc:必须认证才能访问
         * user：user 必须要有记住我 功能才能实现
         * parms：拥有对某个资源的权限才能访问
         * @date  9:33
         * @param securityManager
         * @return org.apache.shiro.spring.web.ShiroFilterFactoryBean
         */
        //拦截
        Map<String,String> filterMap = new LinkedHashMap<>();
        //-----------------------------------------
        //授权 正常情况下，没有授权的会跳转到未授权的页面
        filterMap.put("/user/add","perms[user:add]");//必须要有user:add这个字符串才有权限
        filterMap.put("/user/update","perms[user:update]");

        //----------------------------------
        //filterMap.put("/user/add","authc");
        filterMap.put("/user/*","authc");
        System.out.println(filterMap.toString());
        bean.setFilterChainDefinitionMap(filterMap);


        //设置登录的请求
        bean.setLoginUrl("/user/login");
        //---------------------
        //未授权页面
         bean.setUnauthorizedUrl("/noauth");
        //-------------------------
        
        return bean;
    }
    //DefaultWebSecurityManager  2
    
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRemal")UserRemal userRemal){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联（管理 UserRemal）
        securityManager.setRealm(userRemal);
        return securityManager;
    }
    //创建 remal对象  需要自定义  1
    ////这样就被spring托管
    @Bean(name = "userRemal")
    public UserRemal userRemal(){
        return  new UserRemal();
    }

}
