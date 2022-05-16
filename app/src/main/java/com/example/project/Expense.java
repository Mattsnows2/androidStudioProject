package com.example.project;

public class Expense {
    private String type;
    private float amount;

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Expense(String type,float amount){
        this.type = type;
        this.amount = amount;
    }
}
