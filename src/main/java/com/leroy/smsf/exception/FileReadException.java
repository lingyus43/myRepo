package com.leroy.smsf.exception;

public class FileReadException extends RuntimeException{

    private String msg;

    public FileReadException(String msg) {
        this.msg = msg;
    }
    public String getMsg() { return msg;}

}
