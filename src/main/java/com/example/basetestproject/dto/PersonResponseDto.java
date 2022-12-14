package com.example.basetestproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonResponseDto {
    private Long id;
    private String name;
    private String surname;
    private int age;
}
