package org.keyin.workoutclasses;

// Java and SQL libraries
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.keyin.database.DatabaseConnection;

public class WorkoutClassDAO {

    public void addWorkoutClass(WorkoutClass workoutClass) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        String sql = "INSERT INTO workout_class (workout_class_type, workout_class_description, trainer_id) VALUES (?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, workoutClass.getWorkoutClassType());
        statement.setString(2, workoutClass.getWorkoutClassDescription());
        statement.setInt(3, workoutClass.getTrainerId());

        statement.executeUpdate();
        connection.close();
    }

    public List<WorkoutClass> getAllWorkoutClasses() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        String sql = "SELECT * FROM workout_class";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        List<WorkoutClass> classes = new ArrayList<>();

        while (resultSet.next()) {
            WorkoutClass workoutClass = new WorkoutClass(
                    resultSet.getInt("workout_class_id"),
                    resultSet.getString("workout_class_type"),
                    resultSet.getString("workout_class_description"),
                    resultSet.getInt("trainer_id"));
            classes.add(workoutClass);
        }

        connection.close();
        return classes;
    }

    public void deleteWorkoutClass(int classId) throws SQLException {
        String sql = "DELETE FROM workout_class WHERE workout_class_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, classId);
            stmt.executeUpdate();
        }
    }

}
