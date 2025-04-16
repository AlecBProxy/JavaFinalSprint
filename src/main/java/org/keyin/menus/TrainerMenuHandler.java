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

public class TrainerMenuHandler {

    public static void display(Scanner scanner, User user, UserService userService,
            WorkoutClassService workoutService, MembershipService membershipService) {
        System.out.println("\n\nWelcome " + user.getUsername() + "!");

        do {
            System.out.println("\n=== Trainer Menu ===");
            System.out.println("1. View assigned workout classes");
            System.out.println("2. Create a new workout class");
            System.out.println("3. Update an existing workout class");
            System.out.println("4. Delete a workout class");
            System.out.println("5. Purchase a gym membership");
            System.out.println("6. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.println("");

            switch (choice) {
                case 1 -> viewAssignedClasses(user, workoutService);
                case 2 -> createWorkoutClass(scanner, user, workoutService);
                case 3 -> updateWorkoutClass(scanner, user, workoutService);
                case 4 -> deleteWorkoutClass(scanner, workoutService);
                // Case 5 
                case 5 -> {
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
                        membershipService.addMembership(membership); // Use the passed MembershipService instance
                        System.out.println(" Membership purchased successfully on " + today + "!");
                    } catch (Exception e) {
                        System.out.println(" Error purchasing membership: " + e.getMessage());
                    }
                }
                // Case 6
                case 6 -> {
                    System.out.println("Exiting the trainer menu...");
                    return;
                }
                default -> System.out.println("Invalid choice! Please select a valid option.");
            }

        } while (true);
    }
// Case 1:  View Assigned Workout Classes
    private static void viewAssignedClasses(User user, WorkoutClassService workoutService) {
        try {
            List<WorkoutClass> classes = workoutService.getWorkoutClassesByTrainerId(user.getUserId());

            if (classes.isEmpty()) {
                System.out.println(" No classes assigned to you.");
            } else {
                System.out.println("\n Your Assigned Workout Classes:");
                System.out.printf("%-20s | %s%n", "Workout Type", "Description");
                System.out.println("--------------------------------------------------");

                for (WorkoutClass wc : classes) {
                    System.out.printf("%-20s | %s%n", wc.getWorkoutClassType(), wc.getWorkoutClassDescription());
                }
            }
        } catch (SQLException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }
// Case 2 : Create  Workout Classes

    private static void createWorkoutClass(Scanner scanner, User user, WorkoutClassService workoutService) {
        System.out.print("Enter workout type: ");
        String type = scanner.nextLine();
        System.out.print("Enter workout description: ");
        String desc = scanner.nextLine();
        int trainerId = user.getUserId();

        WorkoutClass wc = new WorkoutClass(0, type, desc, trainerId);

        try {
            workoutService.addWorkoutClass(wc);
            System.out.println(" Workout class added!");
        } catch (SQLException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    // Case 3 :Update Workout Classes

    private static void updateWorkoutClass(Scanner scanner, User user, WorkoutClassService workoutService) {
        try {
            List<WorkoutClass> classes = workoutService.getAllWorkoutClasses();
            if (classes.isEmpty()) {
                System.out.println("  No workout classes available.");
            } else {
                System.out.println("\n Available Workout Classes:");
                System.out.printf("%-5s | %-20s | %-30s%n", "ID", "Type", "Description");
                System.out.println("---------------------------------------------------------------");
                for (WorkoutClass wc : classes) {
                    System.out.printf("%-5d | %-20s | %-30s%n",
                            wc.getWorkoutClassId(),
                            wc.getWorkoutClassType(),
                            wc.getWorkoutClassDescription());
                }
            }
        } catch (SQLException e) {
            System.out.println(" Error displaying workout classes: " + e.getMessage());
        }
        
        int updateId;
        while (true) {
            System.out.print("Enter workout class ID to update: ");
            try {
                updateId = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Please enter a number.");
            }
        }

        System.out.print("Enter new workout type: ");
        String newType = scanner.nextLine();

        System.out.print("Enter new workout description: ");
        String newDesc = scanner.nextLine();

        WorkoutClass updatedWorkout = new WorkoutClass(updateId, newType, newDesc, user.getUserId());

        try {
            boolean updated = workoutService.updateWorkoutClass(updatedWorkout);
            if (updated) {
                System.out.println(" Workout class updated successfully!");
            } else {
                System.out.println(" No workout class found with ID: " + updateId);
            }
        } catch (SQLException e) {
            System.out.println(" Error updating workout class: " + e.getMessage());
        }
    }

    // Case 4 :Delete Workout Classes
    private static void deleteWorkoutClass(Scanner scanner, WorkoutClassService workoutService) {
        try {
            List<WorkoutClass> classes = workoutService.getAllWorkoutClasses();
            if (classes.isEmpty()) {
                System.out.println("  No workout classes available.");
            } else {
                System.out.println("\n Available Workout Classes:");
                System.out.printf("%-5s | %-20s | %-30s%n", "ID", "Type", "Description");
                System.out.println("---------------------------------------------------------------");
                for (WorkoutClass wc : classes) {
                    System.out.printf("%-5d | %-20s | %-30s%n",
                            wc.getWorkoutClassId(),
                            wc.getWorkoutClassType(),
                            wc.getWorkoutClassDescription());
                }
            }
        } catch (SQLException e) {
            System.out.println(" Error displaying workout classes: " + e.getMessage());
        }
        
        int id;
        while (true) {
            System.out.print("Enter workout class ID to delete: ");
            try {
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Please enter a number.");
            }
        }

        try {
            boolean deleted = workoutService.deleteWorkoutClass(id);
            if (deleted) {
                System.out.println(" Workout class deleted!");
            } else {
                System.out.println(" No workout class found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }
}
