package com.yunxf.hello.service;

import com.google.common.collect.Lists;
import com.yunxf.hello.entity.User;
import com.yunxf.hello.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yunxiaofeng on 2015/6/8.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userDao;

    /**
     * 创建用户
     * @param user
     * @return
     */
    @Transactional
    public User create(User user){
        return userDao.save(user);
    }

    public List<User> findAll(){
        return Lists.newArrayList(userDao.findAll());
    }
}
