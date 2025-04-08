package org.keyin.workoutclasses;

// SQL exceptions and list handling
import java.sql.SQLException;
import java.util.List;


// Constructor
public class WorkoutClassService {
    private WorkoutClassDao workoutClassDao;

    public WorkoutClassService() {
        workoutClassDao = new WorkoutClassDao();
    }
    // Method to add a workout class by calling DAO
    public void addWorkoutClass(WorkoutClass workoutClass) throws SQLException {
        workoutClassDao.addWorkoutClass(workoutClass);
    }

    // Method to get all workout classes
    public List<WorkoutClass> getAllWorkoutClasses() throws SQLException {
        return workoutClassDao.getAllWorkoutClasses();
    }

    // Method to delete a workout class by ID
    public void deleteWorkoutClass(int classId) throws SQLException {
        workoutClassDao.deleteWorkoutClass(classId);
    }
}
