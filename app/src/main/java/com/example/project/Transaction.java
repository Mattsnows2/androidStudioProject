package com.example.project;

import java.util.Date;

public class Transaction {

    private String transactions;

    Transaction(String transactions){
        this.transactions=transactions;


    }

    public String getTransactions() {
        return transactions;
    }

    public void setTransactions(String transactions) {
        this.transactions = transactions;
    }
/* private String label;
    private int amount;
    private String currency;
    private Date date;
    private int iconId;



    Transaction(String label, int amount, String currency, int iconId) {
        this.label = label;
        this.amount = amount;
        this.currency = currency;
        this.date = date;
        this.iconId = iconId;
    }

    public String getLabel() {
        return label;
    }

    public int getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public Date getDate() {
        return date;
    }

    public int getIconId() {
        return iconId;
    }*/
}
