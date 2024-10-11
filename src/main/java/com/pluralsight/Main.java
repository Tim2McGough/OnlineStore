package com.pluralsight;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Product> products = new HashMap<String, Product>();

        try {
            // Set up the reader for the CSV file
            BufferedReader reader = new BufferedReader(new FileReader("products.csv"));
            String line;
            reader.readLine(); // Skip header line
            while ((line = reader.readLine()) != null) {
                // Split the line into parts
                String[] parts = line.split("\\|");

                String id = parts[0]; // Product ID (SKU)
                String name = parts[1]; // Product name
                float price = Float.parseFloat(parts[2]); // Product price
                String department = parts[3]; // Product department

                // Create a Product object with all fields
                Product product = new Product(id, name, price, department);

                // Add the Product to the HashMap
                products.put(id, product);
            }

            reader.close(); // Close the file
        } catch (IOException e) {
            System.out.println("A critical error has occurred. Please contact the Dev IMMEDIATELY.");
            e.printStackTrace();
        }

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Main menu loop
        while (running) {
            System.out.println("\nStore Menu:");
            System.out.println("1. Display Products");
            System.out.println("2. Search or Filter Products");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            // Check if the input is an integer
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt(); // Get user input
                scanner.nextLine(); // Consume newline

                // Handle user choice
                switch (choice) {
                    case 1:
                        displayProducts(products);
                        break;
                    case 2:
                        searchOrFilterProducts(products, scanner);
                        break;
                    case 3:
                        running = false;
                        System.out.println("Exiting the application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            } else {
                System.out.println("Invalid input, please enter a number (1, 2, or 3).");
                scanner.nextLine(); // Consume the invalid input
            }
        }


        scanner.close(); // Close the scanner
    }

    // Method to display all products
    private static void displayProducts(HashMap<String, Product> products) {
        System.out.println("\nAvailable Products:");
        for (Product product : products.values()) {
            System.out.println(product);
        }
    }

    // Method to search or filter products
    private static void searchOrFilterProducts(HashMap<String, Product> products, Scanner scanner) {
        System.out.println("\nSearch or Filter Options:");
        System.out.println("1. Search by Name");
        System.out.println("2. Filter by Price");
        System.out.println("3. Filter by Department");
        System.out.print("Enter your choice: ");

        int filterChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (filterChoice) {
            case 1:
                System.out.print("Enter product name to search: ");
                String name = scanner.nextLine();
                searchByName(products, name);
                break;
            case 2:
                System.out.print("Enter maximum price to filter by: ");
                float price = scanner.nextFloat();
                filterByPrice(products, price);
                break;
            case 3:
                System.out.print("Enter department to filter by: ");
                String department = scanner.nextLine();
                filterByDepartment(products, department);
                break;
            default:
                System.out.println("Invalid choice, please try again.");
        }
    }

    // Method to search products by name
    private static void searchByName(HashMap<String, Product> products, String name) {
        System.out.println("\nSearch Results:");
        boolean found = false;
        for (Product product : products.values()) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(product);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No products found with the name: " + name);
        }
    }

    // Method to filter products by price
    private static void filterByPrice(HashMap<String, Product> products, float maxPrice) {
        System.out.println("\nProducts Under $" + maxPrice + ":");
        boolean found = false;
        for (Product product : products.values()) {
            if (product.getPrice() <= maxPrice) {
                System.out.println(product);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No products found under $" + maxPrice);
        }
    }

    // Method to filter products by department
    private static void filterByDepartment(HashMap<String, Product> products, String department) {
        System.out.println("\nProducts in Department: " + department);
        boolean found = false;
        for (Product product : products.values()) {
            if (product.getDepartment().equalsIgnoreCase(department)) {
                System.out.println(product);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No products found in the department: " + department);
        }
    }
}
