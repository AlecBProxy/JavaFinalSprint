package org.keyin.memberships;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.keyin.database.DatabaseConnection;

public class MembershipDAO {

    public void addMembership(Membership membership) throws SQLException {
        String sql = "INSERT INTO membership (user_id, start_date) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, membership.getUserId());
            stmt.setDate(2, Date.valueOf(membership.getStartDate()));
            stmt.executeUpdate();
        }
    }

    // Get one membership by ID
    public Membership getMembershipById(int userId) throws SQLException {
        String sql = "SELECT * FROM membership WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Membership membership = new Membership();
                    membership.setMembershipId(rs.getInt("membership_id"));
                    membership.setUserId(rs.getInt("user_id"));
                    membership.setStartDate(rs.getDate("start_date").toLocalDate());
                    return membership;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving membership: " + e.getMessage());
            throw e;
        }
        return null;
    }

    // Get all memberships
    public List<Membership> getAllMemberships() throws SQLException {
        List<Membership> memberships = new ArrayList<>();
        String sql = "SELECT * FROM membership";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Membership membership = new Membership();
                membership.setMembershipId(rs.getInt("membership_id"));
                membership.setUserId(rs.getInt("user_id"));
                membership.setStartDate(rs.getDate("start_date").toLocalDate());
                memberships.add(membership);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving memberships: " + e.getMessage());
            throw e;
        }
        return memberships;
    }
}
