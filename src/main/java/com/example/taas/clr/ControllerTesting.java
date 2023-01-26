package com.example.taas.clr;

import com.example.taas.beans.Task;
import com.example.taas.beans.UserMapping;
import com.example.taas.dto.TaskDto;
import com.example.taas.dto.TaskPayloadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//@Component
@Order(2)
@RequiredArgsConstructor
public class ControllerTesting implements CommandLineRunner {


    private final RestTemplate restTemplate;


    @Override
    public void run(String... args) throws Exception {

        try {

            Task[] tasks = restTemplate.getForObject("http://localhost:8080/api/tasks", Task[].class);
            List<Task> taskim = Arrays.stream(tasks).collect(Collectors.toList());
            System.out.println(taskim);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            ResponseEntity<String> res2 = restTemplate.getForEntity("http://localhost:8080/api/tasks/1", String.class);
            System.out.println(res2.getStatusCodeValue() == HttpStatus.OK.value() ? "sababa" : "not sababa");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            TaskPayloadDto t10 = TaskPayloadDto.builder()
                    .deadLine(LocalDateTime.now())
                    .group("springz")
                    .info("imformatzia")
                    .caption("koteret")
                    .build();
            ResponseEntity<String> res3 = restTemplate.postForEntity("http://localhost:8080/api/tasks",t10, String.class);
            System.out.println(res3.getStatusCode());
            System.out.println(res3.getStatusCodeValue() == HttpStatus.CREATED.value() ? "sababa" : "not sababa");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



    }
}
