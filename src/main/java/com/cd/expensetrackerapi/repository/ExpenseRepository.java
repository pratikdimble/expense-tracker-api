package com.cd.expensetrackerapi.repository;

import com.cd.expensetrackerapi.model.ExpenseMaster;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseMaster, Long > {
    List<ExpenseMaster> findAllByUserId(Long userId);

    List<ExpenseMaster> findExpenseMasterByExpenseId(Long expenseId);

    List<ExpenseMaster> findAllByExpenseId(Long expenseId);

    List<ExpenseMaster> findAllByUserIdAndIsDeleted(Long userId, Boolean isDeleted);

    @Query(value = "SELECT exp FROM  ExpenseMaster exp WHERE exp.category =:category AND exp.userId=:userId AND exp.isDeleted=false AND lower(exp.title) like CONCAT('%', :keyword, '%') ")
    List<ExpenseMaster> findAllByCategoryAndIsDeletedAndTitleContainingIgnoreCase(Long userId, String category, boolean b, String keyword);

    @Query("SELECT SUM(exp.amount) FROM ExpenseMaster exp WHERE exp.userId=:userId AND  exp.isDeleted=false  AND MONTH(exp.updatedDate) = :month AND YEAR(exp.updatedDate) = :year")
    Double getTotalExpensesForCurrentMonth(Long userId, int month, int year);

    @Query(value = "SELECT exp FROM  ExpenseMaster exp WHERE exp.userId=:userId AND exp.updatedDate BETWEEN :fromDate AND :toDate AND  exp.isDeleted=false ")
    List<ExpenseMaster> findAllByUserIdAndUpdatedDateBetween(Long userId, LocalDate fromDate, LocalDate toDate);

    @Query(value = "SELECT exp FROM  ExpenseMaster exp WHERE exp.userId=:userId AND exp.updatedDate BETWEEN :fromDate AND :toDate AND  exp.isDeleted=false AND exp.category =:category")
    List<ExpenseMaster> findAllByUserIdAndUpdatedDateBetweenAndCategory(LocalDate fromDate, LocalDate toDate, String category, Long userId);

    @Query(value = "SELECT exp FROM  ExpenseMaster exp WHERE exp.userId=:userId AND exp.updatedDate BETWEEN :fromDate AND :toDate AND  exp.isDeleted=false AND exp.category =:category AND lower(exp.title) like CONCAT('%', :keyword, '%')")
    List<ExpenseMaster> findAllByUserIdAndUpdatedDateBetweenAndCategoryAndTitleContainingIgnoreCase(Long userId, LocalDate fromDate, LocalDate toDate, String category, String keyword);



    @Query("SELECT FORMATDATETIME(e.updatedDate, 'yyyy-MMM') AS month, SUM(e.amount) " +
            "FROM ExpenseMaster e WHERE e.userId=:userId AND  e.isDeleted=false " +
            "GROUP BY FORMATDATETIME(e.updatedDate, 'yyyy-MMM') " +
            "ORDER BY month")
    List<Object[]> getMonthlyExpenseSummary(Long userId);

}
