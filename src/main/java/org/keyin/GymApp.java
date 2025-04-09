package org.keyin;

import java.sql.SQLException;
import java.util.Scanner;

import org.keyin.memberships.MembershipService;
import org.keyin.menus.AdminMenuHandler;
import org.keyin.menus.MemberMenuHandler;
import org.keyin.menus.TrainerMenuHandler;
import org.keyin.user.User;
import org.keyin.user.UserService;
import org.keyin.workoutclasses.WorkoutClassService;

public class GymApp {
    public static void main(String[] args) throws SQLException {
        // Initialize services
        UserService userService = new UserService();
        MembershipService membershipService = new MembershipService();
        WorkoutClassService workoutService = new WorkoutClassService();

        // Add default users
        userService.loadDefaultUsers();


        // Scanner for user input
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Placeholder for user object
        User user = new User();
        user.setUserId(1);
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setRole("member");

        do {
            System.out.println("\n=== Gym Management System ===");
            System.out.println("1. Add a new user");
            System.out.println("2. Login as a user");
            System.out.println("3. Exit");
            System.out.println("4. temp: view member menu");
            System.out.println("5. temp: view trainer menu");
            System.out.println("6. temp: view admin menu");
            System.out.print("\nEnter your choice: ");

            // Validate input
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addNewUser(scanner, userService);
                case 2 -> {
                    // logInAsUser(scanner, userService, membershipService, workoutService);
                }
                case 3 -> System.out.println("Exiting the program...");
                case 4 -> {
                    MemberMenuHandler.display(scanner, user, userService, membershipService);
                }

                case 5 -> {
                    TrainerMenuHandler.display(scanner, user, userService, workoutService);

                }
                case 6 -> {
                    AdminMenuHandler.display(scanner, user, userService, membershipService, workoutService);
                }

                default -> System.out.println("Invalid choice! Please select a valid option.");
            }
        } while (choice != 3);

        scanner.close();
    }

    private static void logInAsUser(Scanner scanner, UserService userService, MembershipService membershipService,
            WorkoutClassService workoutService) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // try {
        // User user = userService.loginForUser(username, password);
        // if (user != null) {
        // System.out.println("Login Successful! Welcome " + user.getUserName());
        // switch (user.getUserRole().toLowerCase()) {
        // case "admin":
        // showAdminMenu(scanner, user, userService, membershipService, workoutService);
        // break;
        // case "trainer":
        // // show menu for trainer
        // break;
        // case "member":
        // // show menu for member
        // break;
        // default:

        // break;
        // }
        // } else {
        // System.out.println("Login Failed! Invalid credentials.");
        // }
        // } catch (SQLException e) {
        // System.out.println("An error occurred while logging in.");
        // e.printStackTrace();
        // }
    }
    

    // Minimal implementation of adding a new user
    private static void addNewUser(Scanner scanner, UserService userService) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (Admin/Trainer/Member): ");
        String role = scanner.nextLine();

        // Print user details for debugging
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Role: " + role);

        User user = new User(1, username, password, role);
        try {
            userService.addUser(user);
            System.out.println("User added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }
}