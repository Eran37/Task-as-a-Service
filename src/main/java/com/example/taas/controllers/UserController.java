package com.example.taas.controllers;

import com.example.taas.dto.TaskDto;
import com.example.taas.exceptions.BusinessLogicException;
import com.example.taas.exceptions.TaskSecurityException;
import com.example.taas.security.TokenManager;
import com.example.taas.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final TokenManager tokenManager;

    @GetMapping("tasks")
    public List<TaskDto> getAllTasks(@RequestHeader("Authorization") UUID token) throws TaskSecurityException {
        int userId = tokenManager.getUserId(token);
        return userService.getAllTasks(userId);
    }

    @PostMapping("tasks")
    public void addTask(@RequestHeader("Authorization")UUID token, @RequestBody TaskDto taskDto) throws BusinessLogicException, TaskSecurityException {
        int userId = tokenManager.getUserId(token);
        userService.addTask(userId, taskDto);
    }

//    @GetMapping("{userId}/tasks")
//    public List<TaskDto> getAllTasks(@PathVariable int userId) {
//        return userService.getAllTasks(userId);
//    }
//
//    @PostMapping("{userId}/task")
//    public void addTask(@PathVariable int userId, @Valid @RequestBody TaskDto taskDto) throws BusinessLogicException {
//        userService.addTask(userId, taskDto);

//    }


}
