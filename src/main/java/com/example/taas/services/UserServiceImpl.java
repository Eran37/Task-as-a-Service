package com.example.taas.services;

import com.example.taas.beans.Task;
import com.example.taas.beans.User;
import com.example.taas.dto.TaskDto;
import com.example.taas.exceptions.BusinessLogicException;
import com.example.taas.exceptions.ErrMsg;
import com.example.taas.mapper.TaskMapper;
import com.example.taas.repos.TaskRepository;
import com.example.taas.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final TaskRepository tr;
    private final UserRepository ur;
    private final TaskMapper mapper;

    @Override
    public List<TaskDto> getAllTasks(int userId) {
        return mapper.toDtoList(tr.findByUserId(userId));
    }

    @Override
    public void addTask(int userId, TaskDto taskDto) throws BusinessLogicException {
        Task toAdd = mapper.toDao(taskDto);
        User user = ur.findById(userId).orElseThrow(() -> new BusinessLogicException(ErrMsg.ID_NOT_EXIST));
        toAdd.setUser(user);
        tr.save(toAdd);
    }
}
