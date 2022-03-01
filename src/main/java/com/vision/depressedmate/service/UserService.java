package com.vision.depressedmate.service;

import com.vision.depressedmate.mapper.UserMapper;
import com.vision.depressedmate.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User login(String userId, String password) {
        /*
        비즈니스 로직

        쏼라쏼라
        파라미터 유효성 검증 --- (가) 6줄
        duplicatedLogic()
        조건에 따라 분기처리하여 로직처리
        나, 다, 라,

        --------------
        */
        User user = userMapper.login(userId, password);
        LOGGER.debug("# 로그인한 유저 = {}", user);

        return user;
    }

    public void test(String userId, String password, String phone) {

        // 파라미터 유효성 검증 --- (가) (6줄)
        // duplicatedLogic()

    }

    private String duplicatedLogic() {
        // 6줄
        return "똑같은 결과값";
    }

    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }
}
