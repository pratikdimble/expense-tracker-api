package com.cd.expensetrackerapi.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
//@Data
public class SummaryDto {

    private String monthSpan;
    private Double totalExpenses;

    public String getMonthSpan() {
        return monthSpan;
    }

    public void setMonthSpan(String monthSpan) {
        this.monthSpan = monthSpan;
    }

    public Double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(Double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }
}
