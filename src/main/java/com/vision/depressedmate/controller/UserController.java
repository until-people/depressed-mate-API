package com.vision.depressedmate.controller;

import com.vision.depressedmate.model.User;
import com.vision.depressedmate.model.common.Result;
import com.vision.depressedmate.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
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

    @GetMapping(value = "/getAllUser")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }
}
