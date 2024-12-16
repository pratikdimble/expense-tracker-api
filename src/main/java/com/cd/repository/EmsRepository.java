package com.cd.repository;

import com.cd.model.Employee;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface EmsRepository extends JpaRepository<Employee, UUID > {

    List<Employee> getEmployeeByDepartmentEqualsIgnoreCase(String department);

    List<Employee> findAllByJoiningDateBefore(LocalDate joiningDate);

    List<Employee> findAllByJoiningDateAfter(LocalDate joiningDate);

    List<Employee> findAllByAgeBetween(@NotNull(message = "Age should not be null!") @Min(value=18, message = "Minimum working age 18") @Max(value=60, message = "Maximum working age 60") Integer ageAfter, @NotNull(message = "Age should not be null!") @Min(value=18, message = "Minimum working age 18") @Max(value=60, message = "Maximum working age 60") Integer ageBefore);

    List<Employee> findAllByOrderByJoiningDateAsc();

    List<Employee> findAllByOrderByJoiningDateDesc();
    List<Employee> findEmployeeByNameEqualsIgnoreCase(@NotBlank(message = "Name should not be null!") String name);

}
