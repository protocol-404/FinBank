package models;

public class Transaction {
    public String id;
    public String type;
    public double montant;

    public Transaction(String type, double m) {
        this.type = type;
        this.montant = m;
        this.id = String.valueOf((int)(Math.random() * 10000));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}