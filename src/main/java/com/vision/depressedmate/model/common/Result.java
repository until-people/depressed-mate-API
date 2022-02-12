package com.vision.depressedmate.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {

    private int resultCode = 200;
    private String resultMsg = "";
    private Object resultData = null;

    public Result() {
        this.setResultCode(200);
    }

    public Result(String resultMsg) {
        setResultCode(200);
        setResultMsg(resultMsg);
    }

}
