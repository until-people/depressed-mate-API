package com.vision.depressedmate.controller;

import com.vision.depressedmate.model.User;
import com.vision.depressedmate.model.common.Result;
import com.vision.depressedmate.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Result login(@RequestParam("user_id") String userId,
                        @RequestParam("password") String password) {
        User user = userService.login(userId, password);
        return new Result(200, "SUCCESS", user);
    }
}
