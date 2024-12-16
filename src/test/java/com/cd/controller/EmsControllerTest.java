package com.cd.controller;

import com.cd.model.Employee;
import com.cd.service.IEmsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmsControllerTest {

    @Mock
    private IEmsService  iEmsService;

    @InjectMocks
    private EmsController emsController;

    private Employee testEms;
    private UUID employeeId;

    @BeforeEach
    public void setup() {
        System.out.println(" setup called...");
        employeeId = UUID.randomUUID();
        testEms =new Employee();
        testEms.setEmployeeId(employeeId);
        testEms.setDepartment("DU2");
        testEms.setDesignation("Se Dev");
        testEms.setJoiningDate(LocalDate.of(2024,11,19));
        testEms.setAge(32);
        testEms.setName("Jane");
        testEms.setAddress("NH4 Shivare Pune");
        testEms.setTitle("Spring Boot");
        testEms.setTask("Learn Spring Boot!");
    }

    @Test
    public void testGetEmployees(){
        List<Employee> mockEmployees = Collections.singletonList(testEms);
        when(iEmsService.getEmployees()).thenReturn(mockEmployees);

        ResponseEntity<List<Employee>> response = emsController.getEmployees();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals(testEms, response.getBody().get(0));
        verify(iEmsService).getEmployees();
    }

    @Test
    public void testGetEmployeeById(){
        when(iEmsService.getEmployeeById(employeeId)).thenReturn(testEms);

        ResponseEntity<Employee> response = emsController.getEmployeeById(employeeId.toString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testEms, response.getBody());
        verify(iEmsService).getEmployeeById(employeeId);
    }

    @Test
    public void testAddEmployee() throws URISyntaxException {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        when(iEmsService.addEmployee(any(Employee.class))).thenReturn(testEms);
        ResponseEntity<Employee> response = emsController.addEmployee(testEms);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testEms, response.getBody());
        verify(iEmsService).addEmployee(testEms);
    }

    @Test
    public void testUpdateEmployee(){
        when(iEmsService.updateEmployee(testEms)).thenReturn(testEms);

        Employee updatedEmployee = emsController.updateEmployee(employeeId.toString(), testEms);

        assertEquals(testEms, updatedEmployee);
        verify(iEmsService).updateEmployee(testEms);
    }

    @Test
    public void testUpdateEmployee_ID_Mismatch(){
        when(iEmsService.updateEmployee(testEms)).thenReturn(testEms);

        Employee updatedEmployee = emsController.updateEmployee("9339e256-3fb7-4f98-82bb-637fbc580b01", testEms);

        assertEquals(HttpStatus.NOT_FOUND,updatedEmployee);
        verify(iEmsService).updateEmployee(testEms);
    }

    @Test
    public void testDeleteEmployeeSuccess(){
        when(iEmsService.deleteEmployee(employeeId)).thenReturn(true);

        ResponseEntity<?> response = emsController.deleteEmployee(employeeId.toString());

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(iEmsService).deleteEmployee(employeeId);
    }

    @Test
    public void testDeleteEmployeeNotFound(){
        when(iEmsService.deleteEmployee(employeeId)).thenReturn(false);

        ResponseEntity<?> response = emsController.deleteEmployee(employeeId.toString());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(iEmsService).deleteEmployee(employeeId);
    }

    @Test
    public void testGetTodosByUser(){
        List<Employee> mockEmps = Collections.singletonList(testEms);
        when(iEmsService.getTodosByUser("Jane")).thenReturn(mockEmps);

        ResponseEntity<List<Employee>> response = emsController.getTodosByUser("Jane");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals(testEms, response.getBody().get(0));
        verify(iEmsService).getTodosByUser("Jane");
    }

    @Test
    public void testGetEmployeeByDepartment(){
        List<Employee> mockEmps = Collections.singletonList(testEms);
        when(iEmsService.getEmployeeByDepartment("DU2")).thenReturn(mockEmps);

        ResponseEntity<List<Employee>> response = emsController.getEmployeeByDepartment("DU2");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals(testEms, response.getBody().get(0));
        verify(iEmsService).getEmployeeByDepartment("DU2");
    }

    @Test
    public void testGetEmployeeByAgeCriteria(){
        List<Employee> mockEmps = Collections.singletonList(testEms);
        when(iEmsService.getEmployeeByAgeCriteria(20,30)).thenReturn(mockEmps);

        ResponseEntity<List<Employee>> response = emsController.getEmployeeByAgeCriteria(20,30);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals(testEms, response.getBody().get(0));
        verify(iEmsService).getEmployeeByAgeCriteria(20,30);
    }

    @Test
    public void testGetEmployeeAfterjoiningDate(){
        List<Employee> mockEmps = Collections.singletonList(testEms);
        when(iEmsService.getEmployeeAfterjoiningDate(LocalDate.of(2024,11,25))).thenReturn(mockEmps);

        ResponseEntity<List<Employee>> response = emsController.getEmployeeAfterjoiningDate(LocalDate.of(2024,11,25));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals(testEms, response.getBody().get(0));
        verify(iEmsService).getEmployeeAfterjoiningDate(LocalDate.of(2024,11,25));
    }

    @Test
    public void testEmployeeBeforejoiningDate(){
        List<Employee> mockEmps = Collections.singletonList(testEms);
        when(iEmsService.getEmployeeBeforejoiningDate(LocalDate.of(2024,11,25))).thenReturn(mockEmps);

        ResponseEntity<List<Employee>> response = emsController.getEmployeeBeforejoiningDate(LocalDate.of(2024,11,25));

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals(testEms, response.getBody().get(0));
        verify(iEmsService).getEmployeeBeforejoiningDate(LocalDate.of(2024,11,25));
    }
   /* @Test
    public void testSortEmployeeByJoiningDate(){

    }*/


}