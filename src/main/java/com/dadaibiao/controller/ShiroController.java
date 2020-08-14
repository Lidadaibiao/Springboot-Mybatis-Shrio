package com.dadaibiao.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xuedong Li
 * @version V1.0
 * @Package com.dadaibiao.shiro.controller
 * @date 2020/8/13 17:54
 */
@Controller
public class ShiroController {
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("msg","hellow,shiro");
        return "index";
    }
    @RequestMapping("/user/add")
    public String add(){
        return "/user/add";
    }
    @RequestMapping("/user/update")
    public String update(){
        return "/user/update";
    }
    @RequestMapping("/user/login")
    public String tologin(){
        return "/user/login";
    }

    @RequestMapping("/login")
    public String login(String username, String password,Model model){
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);//执行登录想法
            return "index";
        }catch (UnknownAccountException e){//用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "user/login";

        }catch (IncorrectCredentialsException e){//密码不存在
            model.addAttribute("msg","密码错误");
            return "user/login";
        }
    }

    //未授权页面
    @RequestMapping("/noauth")
    @ResponseBody
    public String noAuth(){
        return "此页面，非VIP禁止进入";
    }
}
