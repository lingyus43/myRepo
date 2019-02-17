package com.leroy.smsf.exception;

public class FormattingException extends RuntimeException {
    private String msg;

    public FormattingException(String msg){
        this.msg = msg;
    }
    public String getMsg() { return msg;}
}
