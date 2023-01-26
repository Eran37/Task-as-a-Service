package com.example.taas.exceptions;

/*
    authentication & authorization exceptions
 */

import lombok.Data;

@Data
public class TaskSecurityException extends Exception {

    private SecurityMsg securityMsg;

    public TaskSecurityException(SecurityMsg securityMsg) {
        super(securityMsg.getMsg());
        this.securityMsg = securityMsg;
    }


}
