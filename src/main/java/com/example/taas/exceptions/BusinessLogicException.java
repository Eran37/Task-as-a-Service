package com.example.taas.exceptions;

public class BusinessLogicException extends Exception {

    public BusinessLogicException(ErrMsg msg) {
        super(msg.getMsg());
    }

}
