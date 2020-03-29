package com.mzx.shirodemo.controller;

import com.mzx.shirodemo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(){
        return "user/login";
    }

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model model) {
        // 把数据存入model
        model.addAttribute("title", "SpringBoot与Shiro整合");
        // 返回test.html
        return "test";
    }

    @RequestMapping("/add")
    public String add() {
        return "user/add";
    }

    @RequestMapping("/update")
    public String update() {
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "user/login";
    }

    @RequestMapping("/noAuth")
    public String noAuth(){
        return "noAuth";
    }

    /**
     * 登陆认证
     */
    @RequestMapping("/login")
    public String login(String username,String password,boolean rememberMe, Model model) {
        //1、获取subject
        Subject subject = SecurityUtils.getSubject();

        //2、封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password,rememberMe);

        //3、执行登录方法

        try {
            //交给Realm处理--->执行它的认证方法
            subject.login(token);
            //登录成功
            return "redirect:/testThymeleaf";

        }catch (UnknownAccountException e){
            //登录失败:用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "user/login";
        }catch (IncorrectCredentialsException e){
            //登录失败：密码错误
            model.addAttribute("msg","密码错误");
            return "user/login";
        }


    }

    //登出账户
    @ResponseBody
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();

        subject.logout();

        return "OUT ok";
    }


}
