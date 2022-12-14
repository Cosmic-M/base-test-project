package com.example.basetestproject.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.basetestproject.model.Person;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    @InjectMocks
    private PersonService personService;
    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person();
    }

    @Test
    void getPersonsAge_theBirthDayToday_ok() {
        int expectedAge = 35;
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDay = currentDate.minusYears(35);
        person.setBirthDate(birthDay);
        Assertions.assertEquals(expectedAge, personService.getPersonsAge(person));
    }

    @Test
    void getPersonsAge_theBirtDayNextDay_ok() {
        int expectedAge = 35;
        LocalDate currentDate = LocalDate.now();
        LocalDate beforeTheBirthDay = currentDate.minusYears(expectedAge + 1).plusDays(1);
        person.setBirthDate(beforeTheBirthDay);
        Assertions.assertEquals(expectedAge, personService.getPersonsAge(person));
    }

    @Test
    void getPersonsAge_doesNotBornYet_notOk() {
        LocalDate currentDate = LocalDate.now();
        LocalDate inTheNearestFuture = currentDate.plusDays(1);
        person.setBirthDate(inTheNearestFuture);
        Exception exc = assertThrows(RuntimeException.class,
                () -> personService.getPersonsAge(person));
        assertTrue(exc.getMessage().startsWith("Wrong input, person doesn't born"));
    }

    @Test
    void getPersonsAge_theBirthDayNotDetected_notOk() {
        assertThrows(NullPointerException.class, () -> personService.getPersonsAge(person));
    }

    @Test
    void getPersonsAge_nullablePerson_notOk() {
        person = null;
        assertThrows(NullPointerException.class, () -> personService.getPersonsAge(person));
    }
}
