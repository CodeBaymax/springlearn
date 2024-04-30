package com.learn.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RepayRequest {

    private String id;

    private String repaySeq;

    private String contNo;

    private BigDecimal transAmt;

    private BigDecimal realAmt;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
