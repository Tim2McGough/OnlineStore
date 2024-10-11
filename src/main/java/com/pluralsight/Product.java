package com.pluralsight;

public class Product {
    private String id; // SKU
    private String name;
    private float price;
    private String department;

    // Constructor
    public Product(String id, String name, float price, String department) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.department = department;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getDepartment() {
        return department;
    }

    // ToString method for easy display
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Price: $" + price + ", Department: " + department;
    }
}
