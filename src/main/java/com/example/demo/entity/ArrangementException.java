package com.example.demo.entity;

import static com.example.demo.entity.ArrangementException.ErrorCode.OK;

public class ArrangementException extends Exception {
    private ErrorCode errorCode = OK;

    public ArrangementException(){}
    public ArrangementException(String message){super(message);}
    public ArrangementException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode(){
        return errorCode;
    }
    public void setErrorCode(ErrorCode errorCode){
        this.errorCode = errorCode;
    }
    public String errorMessage(){
        switch (errorCode){
            case OK:
                return "成功";
            case TELEXEIST:
                return "手机号已注册";
            case CODE_ERROR:
                return "验证码错误";
        }
        return "";
    }
    public enum ErrorCode {
        OK, TELEXEIST,CODE_ERROR
    }
}
