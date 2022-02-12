package com.vision.depressedmate.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vision.depressedmate.model.common.Log;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.core.TimeValue;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Service
public class LogService {

    private final static Logger LOGGER = LoggerFactory.getLogger(LogService.class);
    private final TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {};
    private final RestHighLevelClient restHighLevelClient;
    private final ObjectMapper objectMapper;
    private final HttpServletRequest httpServletRequest;

    @Autowired
    public LogService(RestHighLevelClient restHighLevelClient, ObjectMapper objectMapper, HttpServletRequest httpServletRequest) {
        this.restHighLevelClient = restHighLevelClient;
        this.objectMapper = objectMapper;
        this.httpServletRequest = httpServletRequest;
    }

    /**
     * API 이력 기록
     * HttpServletRequest 이용
     */
    public void recordHistory() {
        Log log = (Log) httpServletRequest.getAttribute("log");
        sendToElastic(log);
    }

    /**
     * API 이력 기록
     * LogDto 객체 이용
     */
    public void recordHistory(Log log) {
        sendToElastic(log);
    }

    // Elasticsearch 저장 (비동기)
    private void sendToElastic(Log log) {
        HashMap<String, Object> sourceMap = objectMapper.convertValue(log, typeRef);
        sourceMap.put("@timestamp", new DateTime(DateTimeZone.UTC).toString());  // toString() 필수
        sourceMap.put("time", new DateTime());  // time 은 toString() 안해도 날짜형으로 저장 잘됨
        LOGGER.debug("# HISTORY LOGGING START => Log = {}, sourceMap = {}", log, sourceMap);

        IndexRequest request = new IndexRequest()
                .index(log.buildIndex())
                .type(log.getType())
                .source(sourceMap, XContentType.JSON)
                .timeout(TimeValue.timeValueSeconds(30));

        restHighLevelClient.indexAsync(request, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                LOGGER.debug("# HISTORY LOGGING END => SUCCESS");
                LOGGER.debug(indexResponse.toString());
            }

            @Override
            public void onFailure(Exception e) {
                LOGGER.debug("# HISTORY LOGGING END => FAIL", e);
                LOGGER.warn("async request error : " + e.getMessage());
            }
        });
    }

}
