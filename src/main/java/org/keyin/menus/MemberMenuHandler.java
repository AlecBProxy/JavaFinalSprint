package org.keyin.menus;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
                    LocalDate purchDate = LocalDate.of(2025, 2, 1);
                    double membershipCost = 100.0;

                    double totalExpenses = calculateMembershipExpenses(purchDate, membershipCost);
                    System.out.println("Membership purchase date:   " + purchDate);
                    System.out.println("Membership cost per month: $" + membershipCost);
                    System.out.printf("\nTotal membership expenses: $%.2f%n", totalExpenses);

                }
                case 4 -> {
                    System.out.println("Exiting the member menu...");
                    return;
                }
                default -> System.out.println("Invalid choice! Please select a valid option.");
            }
        }
    }

    /**
     * Calculate the total membership expenses.
     * 
     * The calculation is based on the number of months (rounded up if there is any
     * partial month)
     * between the purchase date and the current date multiplied by the monthly
     * membership cost.
     * 
     * @param purchaseDate the date when the membership was purchased
     * @param monthlyCost  the cost per month
     * @return the total expense
     */
    private static double calculateMembershipExpenses(LocalDate purchaseDate, double monthlyCost) {
        LocalDate now = LocalDate.now();

        // Calculate full months between the purchaseDate and now
        long monthsBetween = ChronoUnit.MONTHS.between(purchaseDate, now);

        // Check for any additional partial month
        LocalDate adjustedDate = purchaseDate.plusMonths(monthsBetween);
        if (adjustedDate.isBefore(now)) { // there are remaining days in a partial month
            monthsBetween++;
        }

        return monthsBetween * monthlyCost;
    }
}
