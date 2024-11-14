package com.arkadiy.kz.zoo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AnimalDTO {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String type;
    @NotNull
    @Min(0)
    @Max(100)
    private int age;
}
