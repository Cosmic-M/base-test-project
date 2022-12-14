package com.example.basetestproject.controller;

import com.example.basetestproject.dto.PersonResponseDto;
import com.example.basetestproject.model.Person;
import com.example.basetestproject.service.PersonService;
import com.example.basetestproject.service.mapper.PersonMapper;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonMapper personMapper;
    private final PersonService personService;

    @GetMapping("/persons/{id}")
    @ApiOperation(value = "get person from DB by id")
    public PersonResponseDto getPerson(@PathVariable Long id) {
        Person person = personService.getPerson(id);
        PersonResponseDto responseDto = personMapper.toDto(person);
        responseDto.setAge(personService.getPersonsAge(person));
        return responseDto;
    }
}
