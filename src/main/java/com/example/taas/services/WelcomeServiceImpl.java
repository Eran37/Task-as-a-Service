package com.example.taas.services;

import com.example.taas.beans.ClientType;
import com.example.taas.beans.User;
import com.example.taas.exceptions.SecurityMsg;
import com.example.taas.exceptions.TaskSecurityException;
import com.example.taas.repos.UserRepository;
import com.example.taas.security.TokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WelcomeServiceImpl implements WelcomeService {

    private final UserRepository userRepository;
    private final TokenManager tokenManager;

    @Override
    public void register(String email, String password, ClientType clientType) throws TaskSecurityException {


        User user = User.builder()
                .email(email)
                .password(password)
                .build();
        if (userRepository.existsByEmail(email))
            throw new TaskSecurityException(SecurityMsg.EMAIL_ALREADY_EXIST);
        userRepository.save(user);
    }

    @Override
    /*
        login() adding a valid UUID if email & password correct
     */
    public UUID login(String email, String password) throws TaskSecurityException {
        if (!userRepository.existsByEmailAndPassword(email, password)) {
            throw new TaskSecurityException(SecurityMsg.EMAIL_OR_PASSWORD_INCORRECT);
        }
        return tokenManager.add(email);
    }
}
