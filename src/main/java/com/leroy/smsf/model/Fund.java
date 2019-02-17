package com.leroy.smsf.model;

public class Fund {
    private int income;
    private int expense;
    private double taxRate;

    public Fund(int income, int expense, double taxRate) {
        this.income = income;
        this.expense = expense;
        this.taxRate = taxRate;
    }

    public int getIncome() {
        return this.income;
    }

    public int getExpense() {
        return this.expense;
    }

    public double getTaxRate() {
        return this.taxRate;
    }

}
