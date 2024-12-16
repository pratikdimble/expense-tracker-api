package com.cd;

import com.cd.model.Employee;
import com.cd.repository.EmsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class EmsH2Application {

    public static void main(String[] args) {
        SpringApplication.run(EmsH2Application.class, args);
    }

   /* @Autowired
    EmsRepository emsRepository;
    @Override
    public void run(String... args) throws Exception {
        Employee employee=new Employee();
        employee.setDepartment("DU2");
        employee.setDesignation("Se Dev");
        employee.setJoiningDate(LocalDate.of(2024,11,19));
        employee.setAge(32);
        employee.setName("Jane");
        employee.setAddress("NH4 Shivare Pune");
        employee.setTitle("Spring");
        employee.setTask("Learn Spring Boot!");
        Employee employee2=new Employee();
        employee2.setDepartment("DU1");
        employee2.setDesignation("Jr Dev");
        employee2.setJoiningDate(LocalDate.of(2024,11,29));
        employee2.setAge(22);
        employee2.setName("San");
        employee2.setAddress("NH2 Delhi Delhi6");
        employee2.setTitle("VueJs");
        employee2.setTask("Learn Vue Js with Git commands..");
        List<Employee> list = new ArrayList<>();
        list.add(employee);
        list.add(employee2);
        emsRepository.saveAll(list);
    }*/
}
