package com.xxxx;

import com.xxxx.pojo.User;
import com.xxxx.service.UserService;
import org.junit.Before;
import org.junit.Test;

public class UserServiceTest {
    private UserService userService=null;


    @Before
    public void init(){
        System.out.println("测试方法执行前执行.......");
        userService=new UserService();
    }

    @Test
    public void addUser(){
        userService.addUser(new User(3,"333","123456","admin","",""));
    }


    @Test
    public void listUser(){
        userService.listUser();
    }


    @Test
    public void updateUser(){

    }


    @Test
    public void delUser(){

    }
}
