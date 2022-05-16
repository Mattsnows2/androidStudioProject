package Models;


public class Receipts {

    public String label;
    public String categorie;
    public Double operation;

    public Receipts(String label, String categorie, Double operation) {
        this.label = label;
        this.categorie = categorie;
        this.operation = operation;
    }



    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Double getOperation() {
        return operation;
    }

    public void setOperation(Double operation) {
        this.operation = operation;
    }
}
