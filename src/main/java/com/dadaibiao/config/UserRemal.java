package com.dadaibiao.config;

import com.dadaibiao.pojo.Users;
import com.dadaibiao.service.UsersServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xuedong Li
 * @version V1.0
 * @Package com.dadaibiao.shiro.config
 * @date 2020/8/13 18:19
 */
//自定义的remal   需要继承 AuthorizingRealm
public class UserRemal extends AuthorizingRealm {
    @Autowired
    UsersServiceImpl usersService;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了 授权方法==》doGetAuthorizationInfo");
        //-----------------------------------------------------------------
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //info.addStringPermission("user:add"); //给予授权 也就是添加了user:add字符串 和UserRemal中对应
        //从数据库中拿出来 拿到当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        Users users=(Users) subject.getPrincipal();
        //设置权限
        info.addStringPermission(users.getPerms());
        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了 认证方法==>doGetAuthenticationInfo");
        //用户名，密码 数据中区
       /* String username = "root";
        String password = "123456";*/
       //连接真实数据库
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        Users users = usersService.queryUserByUserName(usernamePasswordToken.getUsername());
        if(users==null){//用户为空，说明没有这个用户
             return null;
        }
        //密码认证，shiro自动搞定了，非常Nice 可以加密
        //return new SimpleAuthenticationInfo("",users.getPassWord(),"");

        //-------------------------------------------------------
        //直接将users放到subject中
        return new SimpleAuthenticationInfo(users,users.getPassWord(),"");
        //-------------------------------------------------------
    }
}
