package com.mzx.shirodemo.service;

import com.mzx.shirodemo.pojo.User;

public interface UserService {
    User findById(Integer id);

    User findByName(String username);
}
