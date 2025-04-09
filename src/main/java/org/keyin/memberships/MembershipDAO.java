package org.keyin.memberships;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MembershipDAO {

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/";
    private static final String DB_USER  = "postgres";
    private static final String DB_PASS  = "";

    // SQL Statements //
    private static final String INSERT_MEMBERSHIP_SQL = "INSERT INTO membership (membership_id, member_name, membership_cost, start_date, duration, member_type, status) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?)";
  
    private static final String SELECT_MEMBERSHIP_BY_ID_SQL =
        "SELECT membership_id, member_name, membership_cost, start_date, duration, member_type, status " +
        "FROM membership WHERE membership_id = ?";

    private static final String SELECT_ALL_MEMBERSHIPS_SQL =
        "SELECT membership_id, member_name, membership_cost, start_date, duration, member_type, status FROM membership";

    private static final String UPDATE_MEMBERSHIP_SQL =
        "UPDATE membership SET member_name = ?, membership_cost = ?, start_date = ?, duration = ?, member_type = ?, status = ? " +
        "WHERE membership_id = ?";

    private static final String DELETE_MEMBERSHIP_SQL =
        "DELETE FROM membership WHERE membership_id = ?";


    // Method to create a new membership //
    public void createMembership(Membership membership) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL,DB_USER, DB_PASS);
         PreparedStatement statement = connection.prepareStatement(INSERT_MEMBERSHIP_SQL)) {
            statement.setString(1, membership.getMembershipId());
            statement.setString(2, membership.getMemberName());
            statement.setDouble(3, membership.getMembershipCost());
            statement.setString(4, membership.getStartDate());
            statement.setInt(5, membership.getDuration());
            statement.setString(6, membership.getMemberType());
            statement.setString(7, membership.getStatus());
            statement.executeUpdate();
    } catch (SQLException exception) {
            exception.printStackTrace();
        
    }

    }

    // Method to retrieve a membership by ID //

    public Membership getMembershipById(String membershipId) {
        Membership membership = null; 

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
        PreparedStatement statement = connection.prepareStatement(SELECT_MEMBERSHIP_BY_ID_SQL)) {
            statement.setString(1, membershipId);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                membership = new Membership();
                membership.setMembershipId(result.getString("membership_id"));
                membership.setMemberName(result.getString("member_name"));
                membership.setMembershipCost(result.getDouble("membership_cost"));
                membership.setStartDate(result.getString("start_date"));
                membership.setDuration(result.getInt("duration"));
                membership.setMemberType(result.getString("member_type"));
                membership.setStatus(result.getString("status"));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        
        return membership;
    }

    // Method to retrieve all membership records //
    public List<Membership> getAllMemberships() {
        List<Membership> memberships = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_MEMBERSHIPS_SQL)) {

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Membership membership = new Membership();
                membership.setMembershipId(result.getString("membership_id"));
                membership.setMemberName(result.getString("member_name"));
                membership.setMembershipCost(result.getDouble("membership_cost"));
                membership.setStartDate(result.getString("start_date"));
                membership.setDuration(result.getInt("duration"));
                membership.setMemberType(result.getString("member_type"));
                membership.setStatus(result.getString("status"));
                memberships.add(membership);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    
        return memberships;
    }

    // Method to update memembership //
    public void updateMembership(Membership membership) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement statement = connection.prepareStatement(UPDATE_MEMBERSHIP_SQL)) {

            // membership_id is at the end of the update query //
            statement.setString(1, membership.getMemberName());
            statement.setDouble(2, membership.getMembershipCost());
            statement.setString(3, membership.getStartDate());
            statement.setInt(4, membership.getDuration());
            statement.setString(5, membership.getMemberType());
            statement.setString(6, membership.getStatus());
            statement.setString(7, membership.getMembershipId());

            statement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    // Method to delete a membership //

    public void deleteMembership(String membershipId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement statement = connection.prepareStatement(DELETE_MEMBERSHIP_SQL)) {

            statement.setString(1, membershipId);
            statement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
