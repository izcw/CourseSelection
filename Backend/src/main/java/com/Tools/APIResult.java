package com.Tools;

public class APIResult {
    public int Code;
    public String Msg;
    public Object Data;

    public APIResult() {
    }

    public APIResult(int code, String msg) {
        Code = code;
        Msg = msg;
    }

    public APIResult(int code, String msg, Object data) {
        Code = code;
        Msg = msg;
        Data = data;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }
}
