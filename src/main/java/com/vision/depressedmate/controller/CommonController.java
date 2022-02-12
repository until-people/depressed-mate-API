package com.vision.depressedmate.controller;

import com.vision.depressedmate.model.common.Result;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {

    /**
     * health check API
     */
    @GetMapping(value = "/alivecheck", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result alivecheck() {
        return new Result("SUCCESS");
    }
}
