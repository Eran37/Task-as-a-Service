package com.example.taas.services;

import com.example.taas.dto.TaskDto;
import com.example.taas.dto.UserDto;
import com.example.taas.mapper.TaskMapper;
import com.example.taas.mapper.UserMapper;
import com.example.taas.repos.TaskRepository;
import com.example.taas.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;
    private final UserMapper userMapper;

    @Override
    public int countUsers() {
        return (int) userRepository.count();
    }

    @Override
    public int countTasks() {
        return (int) taskRepository.count();
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskMapper.toDtoList(taskRepository.findAll());
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }
}
