package com.example.taas.exceptions;


import lombok.Getter;

/*
    enum : since java 5 defined as a singleton - eagerly loaded (static) and its private
    because of that cannot define public ctor
 */

@Getter
public enum ErrMsg {

    ID_NOT_EXIST("id not found"),
    ID_ALREADY_EXIST("id already exist"),
    INVALID_DATES("start date must be before end date");

    private String msg;

    ErrMsg(String msg) {
        this.msg = msg;
    }
}
