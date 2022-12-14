package com.example.basetestproject.service.mapper;

import com.example.basetestproject.dto.PersonResponseDto;
import com.example.basetestproject.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public PersonResponseDto toDto(Person person) {
        PersonResponseDto responseDto = new PersonResponseDto();
        responseDto.setId(person.getId());
        responseDto.setName(person.getName());
        responseDto.setSurname(person.getSurname());
        return responseDto;
    }
}
