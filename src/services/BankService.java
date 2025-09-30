package services;

import models.*;
import java.util.*;

public class BankService {
    public static HashMap<String, Client> allClients = new HashMap<>();
    public static int accountCounter = 1000;
    public static Manager currentManager;

    public BankService() {
        currentManager = new Manager("Ousama", "Manager");
    }

    public void addNewClient(String name, String surname) {
        String clientId = "CL" + (int)(Math.random() * 9999);
        Client c = new Client(name, surname, clientId);
        allClients.put(clientId, c);
        System.out.println("client created ID: " + clientId);
    }

    public void makeAccountForClient(String clientId, String type) {
        Client client = allClients.get(clientId);
        if (client != null) {
            String accNum = "ACC" + accountCounter;
            accountCounter++;
            Compte newAccount = new Compte(accNum, type);
            client.addAccount(newAccount);
            System.out.println("account created: " + accNum);
        } else {
            System.out.println("client not found");
        }
    }

    public void makeDepositNow(String clientId, String accountNum, double money) {
        Client c = allClients.get(clientId);
        if (c == null) {
            System.out.println("no client found");
            return;
        }

        Compte acc = c.getAccount(accountNum);
        if (acc == null) {
            System.out.println("no account found");
            return;
        }

        acc.deposit(money);
        System.out.println("neposit succesful new balance: " + acc.getBalance());
    }

    public double checkBal(String clientId, String accountNum) {
        Client c = allClients.get(clientId);
        if (c == null) {
            System.out.println("no client found");
            return 0;
        }

        Compte acc = c.getAccount(accountNum);
        if (acc == null) {
            System.out.println("no account found"); 
            return 0;
        }

        return acc.getBalance();
    }

    public Client getClientById(String id) {
        return allClients.get(id);
    }

    public void listAllClients() {
        System.out.println("\n===== All Clients =====");
        if (allClients.isEmpty()) {
            System.out.println("no clients yet");
        }
        for (String id : allClients.keySet()) {
            Client c = allClients.get(id);
            System.out.println("ID: " + id + " - Name: " + c.name + " " + c.surname);
        }
    }
}