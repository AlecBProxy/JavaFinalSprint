package org.keyin.workoutclasses;

// SQL exceptions and list handling
import java.sql.SQLException;
import java.util.List;

// Constructor
public class WorkoutClassService {
    private final WorkoutClassDAO workoutClassDAO;

    public WorkoutClassService() {
        workoutClassDAO = new WorkoutClassDAO();
    }

    // Method to add a workout class by calling DAO
    public void addWorkoutClass(WorkoutClass workoutClass) throws SQLException {
        workoutClassDAO.addWorkoutClass(workoutClass);
    }

    // Method to get all workout classes
    public List<WorkoutClass> getAllWorkoutClasses() throws SQLException {
        return workoutClassDAO.getAllWorkoutClasses();
    }

    // Method to delete a workout class by ID
    public void deleteWorkoutClass(int classId) throws SQLException {
        workoutClassDAO.deleteWorkoutClass(classId);
    }
}
