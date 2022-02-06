package com.example.misc.entry;
//自定义返回类型
public class ReturnData<T> {
    private Integer code;
    private String msg;
    private T data;

    public ReturnData(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ReturnData(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ReturnData(T data) {
        this.data = data;
    }

}


