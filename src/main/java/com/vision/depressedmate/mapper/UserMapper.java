package com.vision.depressedmate.mapper;

import com.vision.depressedmate.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    void join(User user);

    User login(@Param("userId") String userId, @Param("password") String password);

}
