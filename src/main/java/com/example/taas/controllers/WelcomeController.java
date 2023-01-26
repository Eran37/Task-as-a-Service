package com.example.taas.controllers;

import com.example.taas.dto.LoginReqDto;
import com.example.taas.dto.LoginResDto;
import com.example.taas.dto.RegisterReqDto;
import com.example.taas.exceptions.TaskSecurityException;
import com.example.taas.services.WelcomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/welcome")
@CrossOrigin(origins = "*")
public class WelcomeController {

    private final WelcomeService service;

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED) // err 201 - created
    public void register(@Valid @RequestBody RegisterReqDto regReqDto) throws TaskSecurityException {
        service.register(regReqDto.getEmail(), regReqDto.getPassword(), regReqDto.getClientType());
    }

    @PostMapping("login") // POST because it creates a UUID token
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResDto login(@Valid @RequestBody LoginReqDto reqDto) throws TaskSecurityException {
        UUID token = service.login(reqDto.getEmail(), reqDto.getPassword());
        return new LoginResDto(token);
    }

}
