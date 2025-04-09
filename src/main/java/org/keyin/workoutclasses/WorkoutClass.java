package org.keyin.workoutclasses;

public class WorkoutClass {
    private int workoutClassId;
    private String workoutClassType;
    private String workoutClassDescription;
    private int trainerId;

    // Default constructor
    public WorkoutClass() {
    }

    // Constructor without workoutClassId
    public WorkoutClass(String workoutClassType, String workoutClassDescription, int trainerId) {
        this.workoutClassType = workoutClassType;
        this.workoutClassDescription = workoutClassDescription;
        this.trainerId = trainerId;
    }

    // Full constructor with all fields
    public WorkoutClass(int workoutClassId, String workoutClassType, String workoutClassDescription, int trainerId) {
        this.workoutClassId = workoutClassId;
        this.workoutClassType = workoutClassType;
        this.workoutClassDescription = workoutClassDescription;
        this.trainerId = trainerId;
    }


    // Getter and Setters
    public int getWorkoutClassId() {
        return workoutClassId;
    }

    public void setWorkoutClassId(int workoutClassId) {
        this.workoutClassId = workoutClassId;
    }

    public String getWorkoutClassType() {
        return workoutClassType;
    }

    public void setWorkoutClassType(String workoutClassType) {
        this.workoutClassType = workoutClassType;
    }

    public String getWorkoutClassDescription() {
        return workoutClassDescription;
    }

    public void setWorkoutClassDescription(String workoutClassDescription) {
        this.workoutClassDescription = workoutClassDescription;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    // toString method
    @Override
    public String toString() {
        return "WorkoutClass{" +
                "workoutClassId=" + workoutClassId +
                ", workoutClassType='" + workoutClassType + '\'' +
                ", workoutClassDescription='" + workoutClassDescription + '\'' +
                ", trainerId=" + trainerId +
                '}';
    }
}
