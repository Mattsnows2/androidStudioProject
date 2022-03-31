package com.example.project;

import java.util.List;

public class User {

    public String email;

    public List<String> expenses, receipts;

    public Double capital;

    public User(){

    }

    public User(String email, List<String> expenses, List<String> receipts, Double capital){
        this.email=email;

        this.expenses=expenses;
        this.receipts=receipts;
        this.capital=capital;
    }

}
