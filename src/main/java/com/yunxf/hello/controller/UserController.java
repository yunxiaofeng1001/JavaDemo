package com.yunxf.hello.controller;

import com.yunxf.hello.entity.User;
import com.yunxf.hello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yunxiaofeng on 2015/6/8.
 */
@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.PUT)
    public User create(@RequestBody User user){
        return userService.create(user);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAll(){
        return userService.findAll();
    }
}
