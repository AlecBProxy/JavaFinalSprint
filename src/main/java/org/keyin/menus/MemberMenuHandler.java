package org.keyin.menus;

import java.util.Scanner;

import org.keyin.memberships.MembershipService;
import org.keyin.user.User;
import org.keyin.user.UserService;

public class MemberMenuHandler {
    public static void display(Scanner scanner, User user, UserService userService, MembershipService membershipService) {
        System.out.println("\n\nWelcome " + user.getUsername() + "!");

        while (true) {
            System.out.println("\n=== Member Menu ===");
            System.out.println("1. View workout classes");
            System.out.println("2. Purchase membership");
            System.out.println("3. View total membership expenses");
            System.out.println("4. Exit");

            System.out.print("\nEnter your choice: ");

            // Input validation
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // consume invalid
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.println("");

            switch (choice) {
                case 1 -> {
                    System.out.println("Workout classes under construction.");
                }
                case 2 -> {
                    System.out.println("Membership purchase under construction.");
                }
                case 3 -> {
                    System.out.println("Total membership expenses under construction.");
                }
                case 4 -> {
                    System.out.println("Exiting the member menu...");
                    return;
                }
                default -> System.out.println("Invalid choice! Please select a valid option.");
            }
        }
    }
}
