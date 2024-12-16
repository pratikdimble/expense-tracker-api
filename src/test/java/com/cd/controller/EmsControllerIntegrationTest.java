package com.cd.controller;

import com.cd.model.Employee;
import com.cd.repository.EmsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional // Ensures database is rolled back after each test
class EmsControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private EmsRepository emsRepository;

    // Clear the repository before each test
    @BeforeEach
    void clearRepository() {
        emsRepository.deleteAll();
    }
    @Test
    void testAddAddEmployee_Success() throws Exception {
        mockMvc.perform(post("/ems")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Pratik",
                                  "age": 25,
                                  "address": "NH4 Katraj Pune",
                                  "designation": "Jr Dev",
                                  "department": "DU1",
                                  "joiningDate": "2024-11-29",
                                  "title": "TestTask",
                                  "task": "This is a test task description."
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.employeeId").exists())
                .andExpect(jsonPath("$.name").value("Pratik"))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.designation").value("Jr Dev"))
                .andExpect(jsonPath("$.department").value("DU1"))
                .andExpect(jsonPath("$.title").value("TestTask"))
                .andExpect(jsonPath("$.task").value("This is a test task description."))
        ;
    }

    @Test
    void testAddEmployee_ValidationError() throws Exception {
        mockMvc.perform(post("/ems")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "",
                                  "age": 10,
                                  "address": "Katraj",
                                  "designation": "Jr Dev",
                                  "department": "DU1",
                                  "joiningDate": "2025-11-29",
                                  "title": "Te",
                                  "task": "This is"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("EMS-400"))
                .andExpect(jsonPath("$.errorMessage").value("EMS is Not Valid!"))
                .andExpect(jsonPath("$.fieldErrors", hasSize(5)))

                // Validate individual field errors
                .andExpect(jsonPath("$.fieldErrors[?(@.field=='address')].defaultMessage")
                        .value("Address should be b/w 10-200 characters!"))
                .andExpect(jsonPath("$.fieldErrors[?(@.field=='title')].defaultMessage")
                        .value("Title should be b/w 4-10 characters!"))
                .andExpect(jsonPath("$.fieldErrors[?(@.field=='name')].defaultMessage")
                        .value("Name cannot be null or empty!"))
                .andExpect(jsonPath("$.fieldErrors[?(@.field=='task')].defaultMessage")
                        .value("Task should be b/w 10-100 characters!"))
                .andExpect(jsonPath("$.fieldErrors[?(@.field=='age')].defaultMessage")
                        .value("Minimum working age 18"));
    }

    @Test
    void testUpdateEmployee_IDMismatch() throws Exception {
        Employee testEms =new Employee();
//        testEms.setEmployeeId(null);
        testEms.setDepartment("DU2");
        testEms.setDesignation("Se Dev");
        testEms.setJoiningDate(LocalDate.of(2024,11,19));
        testEms.setAge(32);
        testEms.setName("Jane");
        testEms.setAddress("NH4 Shivare Pune");
        testEms.setTitle("Spring Boot");
        testEms.setTask("Learn Spring Boot!");
        Employee savedEmp = emsRepository.save(testEms);

        mockMvc.perform(put("/ems/{id}", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name": "Pratik",
                                  "age": 25,
                                  "address": "NH4 Katraj Pune",
                                  "designation": "Jr Dev",
                                  "department": "DU1",
                                  "joiningDate": "2025-01-29",
                                  "title": "TestTask",
                                  "task": "This is a test task description."
                            }
                            """.formatted(savedEmp.getEmployeeId().toString())))
                .andDo(print())
                .andExpect(status().isBadRequest())  // Expect 400 due to ID mismatch
                .andExpect(jsonPath("$.errorCode").value("EMS-400"))
                .andExpect(jsonPath("$.errorMessage").value("Employee ID in the path is not same as the ID of Employee!"));
    }

}
