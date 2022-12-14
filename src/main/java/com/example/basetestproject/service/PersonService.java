package com.example.basetestproject.service;

import com.example.basetestproject.model.Person;
import com.example.basetestproject.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public Person getPerson(Long id) {
        Person person = personRepository.findById(id).orElseThrow(()
                -> new RuntimeException("cannot find person by id=" + id));
        return person;
    }

    public int getPersonsAge(Person person) {
        LocalDate birthDay = person.getBirthDate();
        return Period.between(birthDay, LocalDate.now()).getYears();
    }
}
