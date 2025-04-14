package org.keyin.menus;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
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

                    double membershipCost = 50.0; // Monthly cost of membership
                    // Temporary dummy membership data
                    // For now, create a list of dummy memberships with purchase dates and monthly
                    // cost ($50)
                    List<MembershipDummy> dummyMemberships = new ArrayList<>();
                    dummyMemberships.add(new MembershipDummy(LocalDate.of(2025, 1, 15)));
                    dummyMemberships.add(new MembershipDummy(LocalDate.of(2024, 11, 1)));
                    dummyMemberships.add(new MembershipDummy(LocalDate.of(2025, 2, 10)));

                    // Calculate total expenses
                    double totalExpenses = 0;
                    for (MembershipDummy m : dummyMemberships) {
                        totalExpenses += calculateMembershipExpense(m.getPurchaseDate(), membershipCost);
                    }

                    // Print out the details
                    System.out.println("\n=== Memberships and Expenses ===");
                    System.out.println("Membership cost per month: $" + membershipCost);
                    System.out.println("Total number of memberships: " + dummyMemberships.size());
                    System.out.println("Membership purchase dates:");
                    for (MembershipDummy m : dummyMemberships) {
                        System.out.println(" - " + m.getPurchaseDate());
                    }
                    System.out.println("\nTotal membership expenses for all users: $" + totalExpenses);

                }
                case 4 -> {
                    System.out.println("Exiting the admin menu...");
                    return; // Exit to main menu
                }
                default -> System.out.println("Invalid choice! Please select a valid option.");
            }
        }
    }

    /**
     * Helper method to calculate the expense for a membership.
     * Expense is computed based on the number of months (with any partial month
     * rounded up)
     * between the purchaseDate and the current date, multiplied by the monthly
     * cost.
     */
    private static double calculateMembershipExpense(LocalDate purchaseDate, double monthlyCost) {
        LocalDate now = LocalDate.now();
        // Calculate full months between the purchase date and now
        long monthsBetween = ChronoUnit.MONTHS.between(purchaseDate, now);

        // Check if there is a partial month remaining
        LocalDate adjustedDate = purchaseDate.plusMonths(monthsBetween);
        if (adjustedDate.isBefore(now)) {
            monthsBetween++;
        }
        return monthsBetween * monthlyCost;
    }

    // Temporary inner class to simulate a Membership object
    private static class MembershipDummy {
        private final LocalDate purchaseDate;

        public MembershipDummy(LocalDate purchaseDate) {
            this.purchaseDate = purchaseDate;
        }

        public LocalDate getPurchaseDate() {
            return purchaseDate;
        }
    }
}
