package com.example.taas.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDto {

    @Min(0)
    private int id;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String type;

    @Singular
    private List<TaskDto> tasks = new ArrayList<>();

}
