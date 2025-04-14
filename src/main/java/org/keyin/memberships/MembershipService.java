package org.keyin.memberships;

import java.sql.SQLException;
import java.util.List;

public class MembershipService {
    private MembershipDAO membershipDAO = new MembershipDAO();

    public void addMembership(Membership membership) throws SQLException {
        membershipDAO.addMembership(membership);
    }

    public List<Membership> getMembershipsByUserId(int userId) throws SQLException {
        return membershipDAO.getMembershipsByUserId(userId);
    }
}
