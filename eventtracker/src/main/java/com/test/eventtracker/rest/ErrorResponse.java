package com.test.eventtracker.rest;

import java.util.HashMap;
import java.util.Map;


public class ErrorResponse {
    private String service;
    private String requestId;
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    public ErrorResponse() {
    }

    public ErrorResponse(Throwable e) {
        this.message = e.getMessage();
    }



    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ErrorResponse withCode(Integer code) {
        setCode(code);
        return this;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorResponse withMessage(String message) {
        setMessage(message);
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public ErrorResponse withData(Map<String, Object> data) {
        setData(data);
        return this;
    }

}
