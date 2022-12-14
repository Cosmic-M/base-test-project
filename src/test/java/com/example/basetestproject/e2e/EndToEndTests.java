package com.example.basetestproject.e2e;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.basetestproject.BaseTestProjectApplication;
import com.example.basetestproject.dto.PersonResponseDto;
import com.google.gson.Gson;
import java.time.LocalDate;
import java.time.Period;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BaseTestProjectApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application.properties")
@Sql(value = {"/create-persons-table.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/add-persons-to-table.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/drop-persons-table.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class EndToEndTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getPerson_personFromDbExistingId_thenStatus200() throws Exception {
        LocalDate expectedBirthDay = LocalDate.of(1981, 8, 9);
        LocalDate currentDate = LocalDate.now();
        int expectedAge = Period.between(expectedBirthDay, currentDate).getYears();
        String jsonString = mockMvc.perform(get("/persons/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        PersonResponseDto responseDtoList
                = new Gson().fromJson(jsonString, PersonResponseDto.class);
        assertEquals("Ewan", responseDtoList.getName());
        assertEquals("Dobson", responseDtoList.getSurname());
        assertEquals(expectedAge, responseDtoList.getAge());
    }

    @Test
    void getPerson_suchIdIsAbsent_thenStatus400() {
        org.assertj.core.api.Assertions.assertThatThrownBy(
                () -> mockMvc.perform(get("/persons/4"))
                .andExpect(status().isBadRequest()))
                .hasCause(new RuntimeException("cannot find person by id=4"));
    }
}
