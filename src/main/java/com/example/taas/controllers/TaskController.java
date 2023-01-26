package com.example.taas.controllers;

import com.example.taas.dto.TaskDto;
import com.example.taas.dto.TaskPayloadDto;
import com.example.taas.models.DatesBtwReq;
import com.example.taas.exceptions.BusinessLogicException;
import com.example.taas.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/tasks")
@RequiredArgsConstructor //prevents singular dependencies
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskService service;

    @PatchMapping("{/id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateName(@PathVariable int id, @RequestParam String name) {
        service.updateTaskName(id, name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTask(@Valid @RequestBody TaskPayloadDto payloadDto) throws BusinessLogicException {
        service.addTask(new TaskDto(payloadDto));
    }

    @PutMapping("{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTask(@PathVariable int taskId, @RequestBody TaskPayloadDto payloadDto) throws BusinessLogicException {
        service.updateTask(taskId, new TaskDto(payloadDto));
    }

    @GetMapping
    public List<TaskDto> getAllTasks() {
        return service.getAllTasks();
    }

    @GetMapping("{taskId}")
    public TaskDto getOneTask(@PathVariable int taskId) throws BusinessLogicException {
        return service.getTask(taskId);
    }

    @DeleteMapping("{taskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable int taskId) throws BusinessLogicException {
        service.deleteTask(taskId);
    }

    @GetMapping("count")
    public int count() {
        return service.count();
    }

    @GetMapping("sorted/time/asc")
    public List<TaskDto> getAllAsc() {
        return service.getAllTasksOrderedByTimeAsc();
    }

    @GetMapping("sorted/time/desc")
    public List<TaskDto> getAllDesc() {
        return service.getAllTasksOrderedByTimeDesc();
    }

    @GetMapping("between/dates")
    public List<TaskDto> getAllBetween(@RequestBody DatesBtwReq dates) throws BusinessLogicException {
        return service.getAllTasksBetween(dates.getStart(), dates.getEnd());
    }

    // WE CAN WRITE IT THIS WAY TOO BUT TIMESTAMP IS TOO DIFFICULT TO WRITE INTO THE PATH
//    @GetMapping("btw/start/and/end")
//    public List<Task> getAllBetween(@RequestParam Timestamp start, @RequestParam Timestamp end) throws BusinessLogicException {
//        return service.getAllTasksBetween(start,end);
//    }


}
