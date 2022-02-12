package com.vision.depressedmate.controller;

import com.vision.depressedmate.model.common.Log;
import com.vision.depressedmate.model.common.Result;
import com.vision.depressedmate.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class LogController {

    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping(value = "/log/history", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Result recordHistory(@ModelAttribute Log log) {
        logService.recordHistory(log);
        return new Result("SUCCESS");
    }

}
