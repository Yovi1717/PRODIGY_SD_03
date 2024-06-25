package com.mycompany.contact_management_system;

import java.io.*;
import java.util.*;

public class Contact_management_system {

    private static final String FILE_NAME = "contacts.txt";
    private static List<Contact> contacts = new ArrayList<>();

    public static void main(String[] args) {
        loadContacts();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nContact Manager");
            System.out.println("1. Add new contact");
            System.out.println("2. View contacts");
            System.out.println("3. Edit contact");
            System.out.println("4. Delete contact");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addContact(scanner);
                    break;
                case 2:
                    viewContacts();
                    break;
                case 3:
                    editContact(scanner);
                    break;
                case 4:
                    deleteContact(scanner);
                    break;
                case 5:
                    saveContacts();
                    System.out.println("Exiting the program...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addContact(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter email address: ");
        String email = scanner.nextLine();
        contacts.add(new Contact(name, phone, email));
        System.out.println("Contact added successfully.");
    }

    private static void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts to display.");
        } else {
            System.out.println("\nContacts:");
            for (int i = 0; i < contacts.size(); i++) {
                System.out.println((i + 1) + ". " + contacts.get(i));
            }
        }
    }

    private static void editContact(Scanner scanner) {
        viewContacts();
        if (!contacts.isEmpty()) {
            System.out.print("Enter the number of the contact to edit: ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume the newline character
            if (index >= 0 && index < contacts.size()) {
                System.out.print("Enter new name (leave empty to keep current): ");
                String name = scanner.nextLine();
                System.out.print("Enter new phone number (leave empty to keep current): ");
                String phone = scanner.nextLine();
                System.out.print("Enter new email address (leave empty to keep current): ");
                String email = scanner.nextLine();
                Contact contact = contacts.get(index);
                if (!name.isEmpty()) {
                    contact.setName(name);
                }
                if (!phone.isEmpty()) {
                    contact.setPhone(phone);
                }
                if (!email.isEmpty()) {
                    contact.setEmail(email);
                }
                System.out.println("Contact updated successfully.");
            } else {
                System.out.println("Invalid contact number.");
            }
        }
    }

    private static void deleteContact(Scanner scanner) {
        viewContacts();
        if (!contacts.isEmpty()) {
            System.out.print("Enter the number of the contact to delete: ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume the newline character
            if (index >= 0 && index < contacts.size()) {
                contacts.remove(index);
                System.out.println("Contact deleted successfully.");
            } else {
                System.out.println("Invalid contact number.");
            }
        }
    }

    private static void loadContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    contacts.add(new Contact(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("No existing contacts found.");
        }
    }

    private static void saveContacts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Contact contact : contacts) {
                writer.write(contact.getName() + "," + contact.getPhone() + "," + contact.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving contacts.");
        }
    }
}

class Contact {
    private String name;
    private String phone;
    private String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phone + ", Email: " + email;
    }
}
