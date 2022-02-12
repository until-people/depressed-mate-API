package com.vision.depressedmate.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Action {  // 나중에 수정될 가능성 높음. 함께 의논해야 함

    private String action;      // 행위 이름. 예) 요리하기 / 외국어 공부
    private String subject;     // 행위에 대한 주제. 예) 샐러드 / 일본어
    private String content;     // 더 자세한 설명. 예) 냉장고에 있는 야채 2개 이상으로 샐러드 만들기 / 일주일에 2번 일본어 공부
    private String reason;      // 행위를 하는 이유나 목적 ..
    private String place;       // "indoor" or "outdoor"
    @JsonProperty("is_custom")
    private boolean isCustom;   // 유저의 커스텀 행위인가? "T" or "F"
    private String intensity;   // 활동의 강도. 예) 등산: 높음, 호흡명상: 낮음
    private int headcount;      // 필요 인원수. 추후 확장을 위한 필드

}
