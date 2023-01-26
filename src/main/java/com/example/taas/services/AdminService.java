package com.example.taas.services;

import com.example.taas.dto.TaskDto;
import com.example.taas.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {

    int countUsers();
    int countTasks();
    List<TaskDto> getAllTasks();
    List<UserDto> getAllUsers();

}
