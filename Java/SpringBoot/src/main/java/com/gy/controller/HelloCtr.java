package com.gy.controller;

import com.gy.bean.User;
import com.gy.db.MySQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class HelloCtr {
    @Autowired
    MySQL mySQL;


    @RequestMapping("/db")
    @ResponseBody
    public MySQL dbtest(){
        return mySQL;
    }

    @GetMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("msg","hahahhahahahaha");
        return "thymeleaf";
    }

    @GetMapping("/")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String main(User user, HttpSession httpSession,Model model){
        if (!user.getName().isEmpty()&&"123456".equals(user.getPassword())){
            httpSession.setAttribute("userInfo",user);
            System.out.println(user);


            //登录成功重定向解决表单重复提交
            return "redirect:main.html";
        }else{
            model.addAttribute("msg","账号密码错误");
            return "login";
        }


    }

    @GetMapping("/main.html")
    public String mainPage(HttpSession httpSession){
        Object userInfo = httpSession.getAttribute("userInfo");
        System.out.println(userInfo);
        if (userInfo != null){
            return "main";
        }else {
            return "login";
        }

    }


}
