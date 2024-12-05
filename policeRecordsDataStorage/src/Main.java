import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        do {
            try {
                System.out.println("\nMain Menu:");
                System.out.println("1. Enter Data");
                System.out.println("2. Display Data");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1: {
                        // Input TRN and validate it
                        String TRN;
                        while (true) {
                            System.out.print("Enter TRN (5 digits): ");
                            TRN = scanner.nextLine();

                            if (TRN.matches("\\d{5}")) { // Check if TRN is exactly 5 digits
                                if (!DataStorage.isTRNUnique(TRN)) {
                                    System.out.println("Error: TRN already exists. Please enter a unique TRN.");
                                } else {
                                    break; // Valid TRN
                                }
                            } else {
                                System.out.println("Error: TRN must be exactly 5 digits.");
                            }
                        }

                        // Input Email and validate it
                        String email;
                        while (true) {
                            System.out.print("Enter Email: ");
                            email = scanner.nextLine();

                            if (DataStorage.isValidEmail(email)) { // Check email format
                                if (!DataStorage.isEmailUnique(email)) {
                                    System.out.println("Error: Email already exists. Please enter a unique email.");
                                } else {
                                    break; // Valid email
                                }
                            } else {
                                System.out.println("Error: Invalid email format.");
                            }
                        }

                        // Input Date of Birth and Address
                        System.out.print("Enter Date of Birth (dd/mm/yy): ");
                        String DOB = scanner.nextLine();

                        System.out.print("Enter Address: ");
                        String address = scanner.nextLine();

                        // Input Offense and map it to a string
                        String offenseDescription = null;
                        while (true) {
                            System.out.println("Enter Offense (1, 2, 3, or 4): ");
                            System.out.println("1. Arrested for speeding");
                            System.out.println("2. Arrested for stealing");
                            System.out.println("3. Committed a murder");
                            System.out.println("4. Arrested for carrying an illegal firearm");
                            System.out.print("Your choice: ");
                            int offense = scanner.nextInt();
                            scanner.nextLine(); // Consume the leftover newline

                            switch (offense) {
                                case 1:
                                    offenseDescription = "Arrested for speeding";
                                    break;
                                case 2:
                                    offenseDescription = "Arrested for stealing";
                                    break;
                                case 3:
                                    offenseDescription = "Committed a murder";
                                    break;
                                case 4:
                                    offenseDescription = "Arrested for carrying an illegal firearm";
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please enter a valid option.");
                                    continue; // Prompt the user again for valid input
                            }
                            break; // Exit loop if a valid offense is entered
                        }

                        // Write data to the file
                        File file = new File("record.txt");

                        try {
                            if (!file.exists()) {
                                file.createNewFile();
                                System.out.println("File 'record.txt' created!!!");
                            } else {
                                System.out.println("File 'record.txt' already exists!!!");
                            }

                            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                                writer.write("TRN: " + TRN + ", Email: " + email + ", DOB: " + DOB + ", Address: " + address +
                                        ", Offense: " + offenseDescription + "\n");
                            }

                            System.out.println("Record entered successfully!");
                        } catch (IOException e) {
                            System.out.println("An error occurred while writing to the file: " + e.getMessage());
                        }
                        break;
                    }
                    case 2: {
                        System.out.print("Enter the TRN to search: ");
                        String searchTRN = scanner.nextLine();

                        DataStorage dataStorage = new DataStorage();
                        dataStorage.displayRecord(searchTRN);
                        break;
                    }
                    case 0:
                        //DataStorage.clearFile("record.txt"); clear file
                        System.out.println("Exiting the system. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter 0, 1, or 2.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input

            }
        } while (choice != 0);
    }


}

