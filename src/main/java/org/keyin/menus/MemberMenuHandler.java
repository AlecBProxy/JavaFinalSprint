package org.keyin.menus;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.keyin.memberships.Membership;
import org.keyin.memberships.MembershipService;
import org.keyin.user.User;
import org.keyin.user.UserService;
import org.keyin.workoutclasses.WorkoutClass;
import org.keyin.workoutclasses.WorkoutClassService;

public class MemberMenuHandler {
    public static void display(Scanner scanner, User user, UserService userService,
            MembershipService membershipService, WorkoutClassService workoutService) {
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
                    try {
                        List<WorkoutClass> classes = workoutService.getAllWorkoutClasses();
                        if (classes.isEmpty()) {
                            System.out.println("No workout classes available.");
                        } else {
                            System.out.println("\nAvailable Workout Classes:");
                            System.out.printf("%-5s | %-20s | %-30s%n", "ID", "Type", "Description");
                            System.out.println("---------------------------------------------------------------");

                            for (WorkoutClass wc : classes) {
                                System.out.printf("%-5d | %-20s | %-30s%n",
                                        wc.getWorkoutClassId(),
                                        wc.getWorkoutClassType(),
                                        wc.getWorkoutClassDescription());
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Error loading workout classes: " + e.getMessage());
                    }
                }

                case 2 -> {
                    System.out.print("Do you want to purchase a new membership? (yes/no): ");
                    String confirm = scanner.nextLine().trim().toLowerCase();
                    if (!confirm.equals("yes")) {
                        System.out.println(" Membership purchase cancelled.");
                        break;
                    }

                    LocalDate today = LocalDate.now();
                    int userId = user.getUserId();

                    try {
                        Membership membership = new Membership(0, userId, today);
                        membershipService.addMembership(membership);
                        System.out.println(" Membership purchased successfully on " + today + "!");
                    } catch (Exception e) {
                        System.out.println(" Error purchasing membership: " + e.getMessage());
                    }
                }

                case 3 -> {
                    try {
                        Membership membership = membershipService.getMembershipById(user.getUserId());
                        if (membership == null) {
                            System.out.println("No membership found for user ID: " + user.getUserId() +
                                    ". Please purchase a membership first.");
                            break;
                        }
                        int membershipDuration = membershipService.getMembershipDurationInMonths(user.getUserId());
                        double membershipCost = 50.0; // Monthly cost of membership
                        LocalDate purchDate = membership.getStartDate();

                        System.out.println("Membership purchase date:  " + purchDate);
                        System.out.printf("Membership cost per month: $%.2f%n", membershipCost);
                        System.out.printf("\nTotal membership expenses: $%.2f%n", 
                                (membershipCost * membershipDuration));
                    } catch (SQLException e) {
                        System.out.println("Error retrieving membership details: " + e.getMessage());
                    }

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
