package models;

import java.util.ArrayList;

public class Compte {
    public String numero;
    public double solde;
    public String typeCompte;
    public ArrayList<String> historique; // simplified transaction history

    public Compte(String num, String type) {
        this.numero = num;
        this.typeCompte = type;
        this.solde = 0;
        historique = new ArrayList<>();
    }

    public void deposit(double montant) {
        if (montant > 0) {
            solde = solde + montant;
            historique.add("deposit: " + montant);
        }
    }

    public void withdraw(double amount) {
        if (amount <= solde) {
            solde = solde - amount;
            historique.add("withdraw: " + amount);
        } else {
            System.out.println("not enough money");
        }
    }

    public double getBalance() {
        return solde;
    }
}