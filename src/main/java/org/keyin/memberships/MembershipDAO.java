package org.keyin.memberships;

import org.keyin.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembershipDAO {

    public void addMembership(Membership membership) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO membership (user_id, start_date) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, membership.getUserId());
        stmt.setDate(2, Date.valueOf(membership.getStartDate()));
        stmt.executeUpdate();
        conn.close();
    }

    public List<Membership> getMembershipsByUserId(int userId) throws SQLException {
        List<Membership> memberships = new ArrayList<>();
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM membership WHERE user_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            memberships.add(new Membership(
                    rs.getInt("membership_id"),
                    rs.getInt("user_id"),
                    rs.getDate("start_date").toLocalDate()
            ));
        }

        conn.close();
        return memberships;
    }
}
