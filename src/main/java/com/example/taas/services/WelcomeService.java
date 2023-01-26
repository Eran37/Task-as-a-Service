package com.example.taas.services;

import com.example.taas.beans.ClientType;
import com.example.taas.exceptions.TaskSecurityException;

import java.util.UUID;

public interface WelcomeService {

    void register(String email, String password, ClientType clientType) throws TaskSecurityException;

    UUID login(String email, String password) throws TaskSecurityException;

}
