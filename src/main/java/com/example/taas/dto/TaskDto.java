package com.example.taas.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private int identifier;

    @NotBlank
    private String caption;

    @NotBlank
    private String info;

    @NotBlank
    private String group;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime deadLine;

    public TaskDto(TaskPayloadDto payloadDto) {
        this.caption = payloadDto.getCaption();
        this.group = payloadDto.getGroup();
        this.info = payloadDto.getInfo();
        this.deadLine = payloadDto.getDeadLine();
    }

}
