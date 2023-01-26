package com.example.taas.beans;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
public class UserDtoYes implements Serializable {
    @Min(0)
    private final int identifier;
    @NotBlank
    private final String name;
    @NotBlank
    private final String mailAddress;
    @NotBlank
    private final String keyword;
    @NotBlank
    private final ClientType genre;
    @Singular
    private final List<TaskDtoYes> todos;
}
