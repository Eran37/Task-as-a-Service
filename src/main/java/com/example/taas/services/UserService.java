package com.example.taas.services;

import com.example.taas.dto.TaskDto;
import com.example.taas.exceptions.BusinessLogicException;

import java.util.List;

public interface UserService {

    List<TaskDto> getAllTasks(int userId);

    void addTask(int userId, TaskDto taskDto) throws BusinessLogicException;

}
