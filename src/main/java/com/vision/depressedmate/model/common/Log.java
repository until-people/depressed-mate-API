package com.vision.depressedmate.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class Log {

    private final String index = "history_log";
    private final String type = "_doc";
    private Source source;

    public Log() {
        source = new Source();
    }

    @Data
    public static class Source {
        @JsonProperty("serial_number")
        private String serialNumber;        // 시리얼 넘버
        @JsonProperty("remote_ip")
        private String remoteIp;            // API 호출지
        @JsonProperty("step_order")
        private int stepOrder;              // 순서 예) 1, 2, 3 ..
        @JsonProperty("step_title")
        private String stepTitle;           // 프로세스(행위)에 대한 간략 설명. 예) "PUBLISH TO KAFKA"
        @JsonProperty("is_success")
        private String isSuccess;           // 성공 여부. 예) T, F
        @JsonProperty("reason")
        private String reason;              // Fail 일때만 입력
    }

    // 히스토리 로그는 오래 보관할 필요가 없기 때문에 인덱스를 월 단위로 나누고 자주 삭제하도록 한다.
    public String buildIndex() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM");
        return index + "_" + DateTime.now().toString(dateTimeFormatter);
    }
}
