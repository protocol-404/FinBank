package ui;

import services.BankService;
import models.*;
import java.util.Scanner;

public class Main {
    static BankService bankService = new BankService();
    static Scanner scanner = new Scanner(System.in);
    static String currentClientId = null;

    public static void main(String[] args) {
        System.out.println("************************************");
        System.out.println("   Welcome to FinBank Solutions    ");
        System.out.println("************************************\n");

        boolean running = true;
        while (running) {
            System.out.println("\n=== MAIN MENU ==="); 
            System.out.println("1. Manager Login");
            System.out.println("2. Client Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                managerMenu();
            } else if (choice == 2) {
                clientLogin();
            } else if (choice == 3) {
                System.out.println("bye bye!");
                running = false;
            } else {
                System.out.println("invalid option");
            }
        }
    }

    public static void managerMenu() {
        System.out.print("enter manager password: ");
        String pass = scanner.nextLine();

        if (!pass.equals("admin")) {
            System.out.println("Wrong password");
            return;
        }

        System.out.println("\nWelcome Manager");

        boolean inManagerMenu = true;
        while (inManagerMenu) {
            System.out.println("\n--- Manager Options ---");
            System.out.println("1. Create new client");
            System.out.println("2. Create account for client");
            System.out.println("3. View all clients");
            System.out.println("4. Back to main menu");
            System.out.print("Your choice: ");

            int ch = scanner.nextInt();
            scanner.nextLine();

            if (ch == 1) {
                System.out.print("enter client first name: ");
                String fname = scanner.nextLine();
                System.out.print("enter client last name: ");
                String lname = scanner.nextLine();

                bankService.addNewClient(fname, lname);

            } else if (ch == 2) {
                System.out.print("enter client ID: ");
                String clientId = scanner.nextLine();
                System.out.print("Account type (Savings/Checking): ");
                String type = scanner.nextLine();

                bankService.makeAccountForClient(clientId, type);

            } else if (ch == 3) {
                bankService.listAllClients();

            } else if (ch == 4) {
                inManagerMenu = false;
            }
        }
    }

    public static void clientLogin() {
        System.out.print("enter your client ID: ");
        String id = scanner.nextLine();

        Client client = bankService.getClientById(id);
        if (client == null) {
            System.out.println("client not fouund");
            return;
        }

        currentClientId = id;
        System.out.println("\nWelcome " + client.name + "!");

        boolean inClientMenu = true;
        while (inClientMenu) {
            System.out.println("\n--- Client Menu ---");
            System.out.println("1. Check balance");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View my accounts");
            System.out.println("5. Logout");
            System.out.print("choose: ");

            int opt = scanner.nextInt();
            scanner.nextLine();

            if (opt == 1) {
                System.out.print("enter account nbr: ");
                String accNum = scanner.nextLine();
                double bal = bankService.checkBal(currentClientId, accNum);
                System.out.println("your balance is: " + bal + " MAD");

            } else if (opt == 2) {
                System.out.print("enter account nbr: ");
                String accNum = scanner.nextLine();
                System.out.print("amont to deposit: ");
                double amt = scanner.nextDouble();
                scanner.nextLine();

                bankService.makeDepositNow(currentClientId, accNum, amt);

            } else if (opt == 3) {
                System.out.print("enter account nbr: ");
                String accNum = scanner.nextLine();
                System.out.print("amont to withdraw: ");
                double amt = scanner.nextDouble();
                scanner.nextLine();

                Client c = bankService.getClientById(currentClientId);
                Compte acc = c.getAccount(accNum);
                if (acc != null) {
                    acc.withdraw(amt);
                    System.out.println("New balance: " + acc.getBalance());
                } else {
                    System.out.println("Account not found");
                }

            } else if (opt == 4) {
                Client c = bankService.getClientById(currentClientId);
                System.out.println("\nYour accounts:");
                if (c.accounts.isEmpty()) {
                    System.out.println("No accounts yet");
                }
                for (String accNum : c.accounts.keySet()) {
                    Compte acc = c.accounts.get(accNum);
                    System.out.println("- " + accNum + " (" + acc.typeCompte + ") - Balance: " + acc.solde + " MAD");
                }

            } else if (opt == 5) {
                currentClientId = null;
                inClientMenu = false;
                System.out.println("Logged out successfully");
            }
        }
    }
}