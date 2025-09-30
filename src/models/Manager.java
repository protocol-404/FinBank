package models;

public class Manager extends Person {
    public String idManager;

    public Manager(String name, String surname) {
        super(name, surname);
        this.idManager = "MGR001";
    }

    public String getIdManager() {
        return idManager;
    }

    public void setIdManager(String idManager) {
        this.idManager = idManager;
    }
}