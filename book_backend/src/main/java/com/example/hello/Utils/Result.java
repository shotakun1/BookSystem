package com.example.hello.Utils;

public class Result {
    private Boolean code;
    private String msg;
    private Object data;

    public Result() {
    }

    public Result(Boolean code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Boolean getCode() {
        return code;
    }

    public void setCode(Boolean code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result success() {
        return new Result(true, "success", null);
    }

    public static Result success(Object data) {
        return new Result(true, "success", data);
    }

    public static Result error(String msg) {
        return new Result(false, msg, null);
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
