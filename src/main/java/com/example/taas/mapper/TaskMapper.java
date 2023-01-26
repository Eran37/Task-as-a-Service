package com.example.taas.mapper;

import com.example.taas.beans.Task;
import com.example.taas.dto.TaskDto;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper implements Mapper<Task, TaskDto>{

    public Task toDao(TaskDto taskDto) {
        return Task.builder()
                .id(taskDto.getIdentifier())
                .title(taskDto.getCaption())
                .classification(taskDto.getGroup())
                .description(taskDto.getInfo())
                .when(Timestamp.valueOf(taskDto.getDeadLine()))
                .build();
    }

    public TaskDto toDto(Task task) {
        return TaskDto.builder()
                .identifier(task.getId())
                .caption(task.getTitle())
                .group(task.getClassification())
                .info(task.getDescription())
                .deadLine(task.getWhen().toLocalDateTime())
                .build();
    }

    public List<Task> toDaoList(List<TaskDto> dtoList) {
        return dtoList.stream().map(this::toDao).collect(Collectors.toList());
    }

    public List<TaskDto> toDtoList(List<Task> daoList) {
        return daoList.stream().map(this::toDto).collect(Collectors.toList());
    }


}
