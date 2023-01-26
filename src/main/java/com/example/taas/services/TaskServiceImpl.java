package com.example.taas.services;

import com.example.taas.dto.TaskDto;
import com.example.taas.exceptions.BusinessLogicException;
import com.example.taas.exceptions.ErrMsg;
import com.example.taas.mapper.TaskMapper;
import com.example.taas.repos.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor //prevents circular dependencies ! AND ! looks short and clean
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final TaskMapper mapper;


    @Override
    public void updateTaskName(int id, String name) {
        taskRepository.updateName(name, id);
    }

    @Override
    public void addTask(TaskDto taskDto) throws BusinessLogicException {
        if(taskRepository.existsById(taskDto.getIdentifier())) {
            throw new BusinessLogicException(ErrMsg.ID_ALREADY_EXIST);
        }
        taskRepository.save(mapper.toDao(taskDto));
    }

    @Override
    public void updateTask(int taskId, TaskDto taskDto) throws BusinessLogicException {
        taskDto.setIdentifier(taskId);
        if(!taskRepository.existsById(taskId))
            throw new BusinessLogicException(ErrMsg.ID_NOT_EXIST);
        taskRepository.saveAndFlush(mapper.toDao(taskDto));
    }


    @Override
    public void deleteTask(int taskId) throws BusinessLogicException {
        if(!taskRepository.existsById(taskId))
            throw new BusinessLogicException(ErrMsg.ID_NOT_EXIST);
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return mapper.toDtoList(taskRepository.findAll());
    }

    @Override
    public TaskDto getTask(int taskId) throws BusinessLogicException {
        //supplier lambda: gets no parameters and throws the exception that the developer supplying to it (vs regular lambda - not supplier)
        return mapper.toDto(taskRepository.findById(taskId).orElseThrow(() -> new BusinessLogicException(ErrMsg.ID_NOT_EXIST)));
    }

    @Override
    public int count() {
        return (int) taskRepository.count();
    }

    @Override
    public List<TaskDto> getAllTasksOrderedByTimeAsc() {
        return mapper.toDtoList(taskRepository.findByOrderByWhenAsc());
    }

    @Override
    public List<TaskDto> getAllTasksOrderedByTimeDesc() {
        return mapper.toDtoList(taskRepository.findByOrderByWhenDesc());
    }

    @Override
    public List<TaskDto> getAllTasksBetween(Timestamp startDate, Timestamp endDate) throws BusinessLogicException {
        if(endDate.before(startDate))
            throw new BusinessLogicException(ErrMsg.INVALID_DATES);
        return mapper.toDtoList(taskRepository.findByWhenBetween(startDate, endDate));

    }
}
