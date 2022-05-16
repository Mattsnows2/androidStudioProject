package com.example.project;

public class Expense {
    private String type;
    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Expense(String type,Double amount){
        this.type = type;
        this.amount = amount;
    }
}
