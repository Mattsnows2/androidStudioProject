package Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;
    public String email;

    public int expenses, receipts;

    public Double capital;

    public User(int id, String email, int expenses, int receipts, Double capital) {

    }

    public User(String id,String email, int expenses, int receipts, Double capital) {
        this.email = email;

        this.expenses = expenses;
        this.receipts = receipts;
        this.capital = capital;
    }

    public void User2(int id, String email, int expenses, int receipts, Double capital){
        this.email = email;

        this.expenses = expenses;
        this.receipts = receipts;
        this.capital = capital;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getExpenses() {
        return expenses;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

    public int getReceipts() {
        return receipts;
    }

    public void setReceipts(int receipts) {
        this.receipts = receipts;
    }

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }
}
