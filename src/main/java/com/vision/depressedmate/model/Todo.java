package com.vision.depressedmate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @JsonProperty("user_id")
    private String userId;
    private String title;
    private String content;
    @JsonProperty("start_time")
    private String startTime;
    @JsonProperty("end_time")
    private String endTime;
    @JsonProperty("repeat_cycle")
    private String repeatCycle;
    @JsonProperty("created_at")
    private String createdAt;
}
