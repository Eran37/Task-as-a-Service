package com.example.taas.clr;

import com.example.taas.beans.Task;
import com.example.taas.mapper.TaskMapper;
import com.example.taas.services.TaskService;
import com.example.taas.utils.Art;
import com.example.taas.utils.TestingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;


//@Component
//@Order(2)
@RequiredArgsConstructor
public class ServiceTesting implements CommandLineRunner {

    private final TaskService service;
    private final TaskMapper mapper;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(Art.SERVICE_TESTING);

        Task toAdd1 = Task.builder()
                .classification("Web")
                .title("HTML + CSS")
                .description("online kit with Amit")
                .when(Timestamp.valueOf(LocalDateTime.now().plusDays(6)))
                .build();

        Task toAdd2 = Task.builder()
                .classification("Web")
                .title("JS")
                .description("online kit with Idan")
                .when(Timestamp.valueOf(LocalDateTime.now().plusDays(19)))
                .build();

        TestingUtils.printCaption("add tasks:");
        service.addTask(mapper.toDto(toAdd1));
        service.addTask(mapper.toDto(toAdd2));
        service.getAllTasks().forEach(System.out::println);


        TestingUtils.printCaption("update task:");

        Task toUpdate = mapper.toDao(service.getTask(3));
        toUpdate.setClassification("Frontend");
        service.updateTask(3, mapper.toDto(toUpdate));
        System.out.println(service.getTask(3));

        TestingUtils.printCaption("get single task:");
        try { service.getTask(800);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(service.getTask(3));

        TestingUtils.printCaption("count:");
        System.out.println(service.count());

        TestingUtils.printCaption("get all tasks");
        service.getAllTasks().forEach(System.out::println);

        TestingUtils.printCaption("get all tasks by date asc");
        service.getAllTasksOrderedByTimeAsc().forEach(System.out::println);

        TestingUtils.printCaption("get all tasks by date desc");
        service.getAllTasksOrderedByTimeDesc().forEach(System.out::println);

        TestingUtils.printCaption("get all tasks between");
        Timestamp d1 = Timestamp.valueOf(LocalDateTime.now().minusDays(10));
        Timestamp d2 = Timestamp.valueOf(LocalDateTime.now().plusDays(10));
        service.getAllTasksBetween(d1,d2).forEach(System.out::println);

        TestingUtils.printCaption("delete task");
        service.deleteTask(1);
        service.getAllTasks().forEach(System.out::println);


    }
}
