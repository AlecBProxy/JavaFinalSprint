package org.keyin.memberships;
public class MembershipDAO {

    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/";
    private static final String DB_USER  = "postgres";
    private static final String DB_PASS  = "";


    // SQL Statements //
    private static final String INSERT_MEMBBERSHIP_SQL = "INSERT INTO membership (membership_id, member_name, membership_cost, start_date, duration, member_type, status) " +
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
}
