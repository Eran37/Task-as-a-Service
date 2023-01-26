package com.example.taas.beans;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private ClientType clientType;
    @Singular
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
    // mappedBy -> another column, in Task table: "user_id"
    private List<Task> tasks = new ArrayList<>();

}
