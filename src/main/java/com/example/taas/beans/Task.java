package com.example.taas.beans;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, name = "task_group")
    private String classification;

    /*
    Date of sql <- matches with -> LocalDate
    Timestamp  <- matches with -> LocalDateTime
     */
    @Column(name = "task_when")
    private Timestamp when;

    @ManyToOne
    @ToString.Exclude
    private User user;


}
