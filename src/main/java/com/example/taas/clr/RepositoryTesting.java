package com.example.taas.clr;

import com.example.taas.beans.ClientType;
import com.example.taas.beans.Task;
import com.example.taas.beans.User;
import com.example.taas.repos.TaskRepository;
import com.example.taas.repos.UserRepository;
import com.example.taas.utils.Art;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
@Order(1)
@RequiredArgsConstructor // of lombok
public class RepositoryTesting implements CommandLineRunner {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) {

        System.out.println("\n" + Art.REPOSITORY_TESTING + "\n");

        User u1 = User.builder()
                .email("admin@gmail.com")
                .password("1234")
                .clientType(ClientType.ADMINISTRATOR)
                .build();

        User u2 = User.builder()
                .email("ariel@gmail.com")
                .password("1234")
                .clientType(ClientType.USER)
                .build();

        User u3 = User.builder()
                .email("bar@gmail.com")
                .password("1234")
                .clientType(ClientType.USER)
                .build();


        Task t1 = Task.builder()
                .classification("spring")
                .title("Cat & Toy Exc")
                .description("spring homework")
                .when(Timestamp.valueOf(LocalDateTime.now().plusWeeks(2)))
                .user(u2)
                .build();

        Task t2 = Task.builder()
                .classification("spring")
                .title("Author & Books Exc")
                .description("spring homework")
                .when(Timestamp.valueOf(LocalDateTime.now().plusWeeks(2)))
                .user(u2)
                .build();

        Task t3 = Task.builder()
                .classification("spring")
                .title("prepare for spring Exam")
                .description("spring homework")
                .when(Timestamp.valueOf(LocalDateTime.of(2022,6,11,12,00)))
                .user(u3)
                .build();

        u2.setTasks(Arrays.asList(t1,t2));
        u3.setTasks(List.of(t3));

        userRepository.saveAll(Arrays.asList(u1,u2,u3));
        userRepository.findAll().forEach(System.out::println);

        System.out.println("\ntasks of u2");
        taskRepository.findByUserId(2).forEach(System.out::println);

        System.out.println("\ntasks of u3");
        taskRepository.findByUserId(3).forEach(System.out::println);



    }
}
