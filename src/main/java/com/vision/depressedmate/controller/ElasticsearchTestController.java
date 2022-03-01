package com.vision.depressedmate.controller;

import com.vision.depressedmate.model.common.Result;
import com.vision.depressedmate.service.ElasticsearchTestService;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/elastic")
public class ElasticsearchTestController {

    private final ElasticsearchTestService elasticsearchTestService;

    @Autowired
    public ElasticsearchTestController(ElasticsearchTestService elasticsearchTestService) {
        this.elasticsearchTestService = elasticsearchTestService;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Result createDocument(@RequestParam("index") String index,
                                 @RequestParam("activity") String activity) throws IOException {

        IndexResponse response = elasticsearchTestService.createDocument(index, activity);

        Result result = new Result("SUCCESS");
        result.setResultData(response);

        return result;
    }

    @PostMapping("/post")
    public IndexResponse testInserting() throws IOException {
        return elasticsearchTestService.testInserting();
    }
}
