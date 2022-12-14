package com.example.basetestproject.service.mapper;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.basetestproject.dto.PersonResponseDto;
import com.example.basetestproject.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PersonMapperTest {
    @InjectMocks
    private PersonMapper personMapper;
    private Person person;
    private PersonResponseDto personResponseDto;

    @BeforeEach
    void setUp() {
        person = new Person();
        person.setId(1L);
        person.setName("John");
        person.setSurname("Dou");

        personResponseDto = new PersonResponseDto();
        personResponseDto.setId(1L);
        personResponseDto.setName("John");
        personResponseDto.setSurname("Dou");
    }

    @Test
    void toDto_validData_ok() {
        PersonResponseDto actual = personMapper.toDto(person);
        Assertions.assertEquals(personResponseDto.getId(), actual.getId());
        Assertions.assertEquals(personResponseDto.getName(), actual.getName());
        Assertions.assertEquals(personResponseDto.getSurname(), actual.getSurname());
    }

    @Test
    public void toDto_nullablePerson_notOk() {
        person = null;
        assertThrows(NullPointerException.class, () -> personMapper.toDto(person));
    }
}
