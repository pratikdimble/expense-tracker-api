package com.cd.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Employee extends Person{

    @Id
    @GeneratedValue
    private UUID employeeId;

    @NotBlank(message = "Designation cannot be null or empty!")
    private String designation;

    @NotBlank(message = "Department cannot be null or empty!")
    private String department;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate joiningDate;
}
