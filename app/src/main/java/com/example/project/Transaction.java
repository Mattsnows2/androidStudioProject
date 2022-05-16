package com.example.project;

import java.util.Date;

public class Transaction {
    private String label;
    private int amount;
    private String currency;
    //private Date date;
    private int iconId;
    private String category;
    //private int type; //1: receipts, 2: expenses

    Transaction(String label, int amount, String currency, int iconId, String category) {
        this.label = label;
        this.amount = amount;
        this.currency = currency;
        this.category = category;
        //this.date = date;
        this.iconId = iconId;
        //this.type = type;
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

   // public Date getDate() { return date; }

    public int getIconId() { return iconId; }

    public String getCategory() { return category; }

    //public int getType() { return type; }
}
