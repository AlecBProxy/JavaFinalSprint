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

    public boolean updateWorkoutClass(WorkoutClass workoutClass) throws SQLException {
        return workoutClassDAO.updateWorkoutClass(workoutClass);
    }

    public List<WorkoutClass> getWorkoutClassesByTrainerId(int trainerId) throws SQLException {
        return workoutClassDAO.getWorkoutClassesByTrainerId(trainerId);
    }

    // Method to delete a workout class by ID
    public boolean deleteWorkoutClass(int classId) throws SQLException {
        return workoutClassDAO.deleteWorkoutClass(classId);
    }
    
}
