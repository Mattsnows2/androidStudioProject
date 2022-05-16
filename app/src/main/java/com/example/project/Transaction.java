package com.example.project;

import java.util.Date;

public class Transaction {
    private String label;
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
    }
}
