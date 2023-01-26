package com.example.taas.beans;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class TaskDtoYes implements Serializable {
    private final int id;
    private final String title;
    private final String description;
    private final String classification;
    private final Timestamp when;
}
