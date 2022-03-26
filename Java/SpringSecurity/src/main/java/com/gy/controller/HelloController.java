package com.gy.controller;

import com.gy.bean.Book;
import com.gy.dao.UserDaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private UserDaoMapper userDaoMapper;

    @GetMapping("/hello")
    public Book hello() {
        return userDaoMapper.selectByName("gy");
    }



}
