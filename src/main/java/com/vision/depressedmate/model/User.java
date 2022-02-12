package com.vision.depressedmate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @JsonProperty("user_id")
    private String userId;
    private String password;
    private String nickname;
    private String email;
    private String mobile;
    private String birth;
    private String interest;
    @JsonProperty("created_at")
    private String createdAt;

}
