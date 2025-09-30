package models;

import java.util.HashMap;

public class Client extends Person {
    public String idClient;
    public HashMap<String, Compte> accounts;

    public Client(String name, String surname, String id) {
        super(name, surname);
        this.idClient = id;
        accounts = new HashMap<>();
    }

    public void addAccount(Compte c) {
        accounts.put(c.numero, c);
    }

    public Compte getAccount(String num) {
        return accounts.get(num);
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public HashMap<String, Compte> getAccounts() {
        return accounts;
    }

    public void setAccounts(HashMap<String, Compte> accounts) {
        this.accounts = accounts;
    }
}