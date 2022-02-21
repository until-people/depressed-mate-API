package com.vision.depressedmate.controller;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.core.TimeValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ElasticsearchTestController {

    private final RestHighLevelClient restHighLevelClient;

    public ElasticsearchTestController(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @PostMapping("/post")
    public IndexResponse testInserting() throws IOException {
        Map<String, Object> source = new HashMap<>();
        source.put("message", "hello, world!");
        source.put("message1", "hello, world!");
        source.put("message2", "hello, world!");
        source.put("message3", "hello, world!");
        source.put("message4", "hello, world!");
        source.put("message5", "hello, world!");
        String index = "test";

        IndexRequest request = new IndexRequest()
                .index(index)
                .source(source, XContentType.JSON)
                .timeout(TimeValue.timeValueSeconds(30));

        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        return response;
    }
}
