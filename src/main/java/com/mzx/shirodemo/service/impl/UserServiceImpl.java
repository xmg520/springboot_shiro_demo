package com.mzx.shirodemo.service.impl;

import com.mzx.shirodemo.dao.UserDao;
import com.mzx.shirodemo.pojo.User;
import com.mzx.shirodemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public User findByName(String username) {
        return userDao.findByName(username);
    }
}
