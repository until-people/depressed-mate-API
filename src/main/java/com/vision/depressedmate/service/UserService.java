package com.vision.depressedmate.service;

import com.vision.depressedmate.mapper.UserMapper;
import com.vision.depressedmate.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User login(String userId, String password) {
        User user = userMapper.login(userId, password);
        LOGGER.debug("# 로그인한 유저 = {}", user);

        return user;
    }
}
