package com.vision.depressedmate.service;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.core.TimeValue;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ElasticsearchTestService {

    // Client => 통신을 돕는 매개체
    private final RestHighLevelClient restHighLevelClient;

    @Autowired
    public ElasticsearchTestService(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    public IndexResponse createDocument(String index, String activity) throws IOException {
        Map<String, Object> sourceMap = new HashMap<>();
        sourceMap.put("timestamp", generateTimestamp());
        // TODO: activity NULL CHECK
        sourceMap.put("activity", activity);
        sourceMap.put("developer", "dam0");

        IndexRequest request = new IndexRequest()
                .index(index) // 동적 변화
                .source(sourceMap, XContentType.JSON)  // 동적변화
                .timeout(TimeValue.timeValueSeconds(30));

        // index => 새로운 인덱스를 가진 다큐먼트를 저장할게~
        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);

        return response;
    }

    private String generateTimestamp() {
        return new DateTime(DateTimeZone.UTC).toString();
    }

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

        return restHighLevelClient.index(request, RequestOptions.DEFAULT);
    }
}
