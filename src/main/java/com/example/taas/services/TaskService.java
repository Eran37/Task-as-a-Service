package com.example.taas.services;

import com.example.taas.dto.TaskDto;
import com.example.taas.exceptions.BusinessLogicException;

import java.sql.Timestamp;
import java.util.List;

public interface TaskService {

    void addTask(TaskDto taskDto) throws BusinessLogicException;
    void updateTask(int taskId, TaskDto taskDto) throws BusinessLogicException;
    void updateTaskName(int id, String name);
    void deleteTask(int taskId) throws BusinessLogicException;

    List<TaskDto> getAllTasks();
    TaskDto getTask(int taskId) throws BusinessLogicException;

    int count();

    List<TaskDto> getAllTasksOrderedByTimeAsc();
    List<TaskDto> getAllTasksOrderedByTimeDesc();
    List<TaskDto> getAllTasksBetween(Timestamp startDate, Timestamp endDate) throws BusinessLogicException;




}
