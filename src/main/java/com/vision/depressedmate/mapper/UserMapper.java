package com.vision.depressedmate.mapper;

import com.vision.depressedmate.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    void join(User user);

    User login(@Param("userId") String userId, @Param("password") String password);

    List<User> getAllUser();

    /*
    * 예전에는 DAO
    * @Repository
    * SqlSessionFactory
    * SqlSessionTemplate
    * SQL 문을
    *
    *
    * */

}
