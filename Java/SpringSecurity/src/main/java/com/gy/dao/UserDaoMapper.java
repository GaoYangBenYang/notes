package com.gy.dao;

import com.gy.bean.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDaoMapper {

    //根据userName查询用户信息
    Book selectByName(String userName);


}
