package org.keyin.menus;

import java.util.Scanner;

import org.keyin.memberships.MembershipService;
import org.keyin.user.User;
import org.keyin.user.UserService;
import org.keyin.workoutclasses.WorkoutClassService;

public class AdminMenuHandler {

    public static void display(Scanner scanner, User user, UserService userService,
            MembershipService membershipService, WorkoutClassService workoutService) {
        System.out.println("\n\nWelcome " + user.getUsername() + "!");

        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. View all users");
            System.out.println("2. Delete a user");
            System.out.println("3. View all memberships and total expenses");
            System.out.println("4. Exit");
            System.out.print("\nEnter your choice: ");

            // Validate input
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // consume invalid input
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.println("");

            switch (choice) {
                case 1 -> {
                    try {
                        var users = userService.getAllUsers();
                        if (users.isEmpty()) {
                            System.out.println("No users found.");
                        } else {
                            System.out.println("=== All Registered Users ===");
                            for (User u : users) {
                                System.out.println("ID: " + u.getUserId());
                                System.out.println("Username: " + u.getUsername());
                                System.out.println("Email: " + u.getEmail());
                                System.out.println("Phone: " + u.getPhoneNumber());
                                System.out.println("Address: " + u.getAddress());
                                System.out.println("Role: " + u.getRole());
                                System.out.println("-------------------------");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Error retrieving users: " + e.getMessage());
                    }
                    break;
                }
                
                case 2 -> {
                    System.out.print("Enter the ID of the user to delete: ");
                    int userIdToDelete = Integer.parseInt(scanner.nextLine());
                
                    try {
                        boolean success = userService.deleteUser(userIdToDelete);
                        if (success) {
                            System.out.println("User deleted.");
                        } else {
                            System.out.println("User not found.");
                        }
                    } catch (Exception e) {
                        System.out.println("An error occurred while deleting the user: " + e.getMessage());
                    }
                    break;
                }
                case 3 -> {

                    System.out.println("View all memberships and total expenses under construction.");

                }
                case 4 -> {
                    System.out.println("Exiting the admin menu...");
                    return; // Exit to main menu
                }
                default -> System.out.println("Invalid choice! Please select a valid option.");
            }
        }
    }
}
