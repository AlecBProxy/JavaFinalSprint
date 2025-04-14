package org.keyin.memberships;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MembershipService {
    private MembershipDAO membershipDAO = new MembershipDAO();

    public void addMembership(Membership membership) throws SQLException {
        membershipDAO.addMembership(membership);
    }

    public Membership getMembershipById(int userId) throws SQLException {
        return membershipDAO.getMembershipById(userId);
    }

    public List<Membership> getAllMemberships() throws SQLException {
        return membershipDAO.getAllMemberships();
    }

    // Function to get number of months a member has had a membership for, rounded
    // up
    public int getMembershipDurationInMonths(int userId) throws SQLException {
        Membership membership = getMembershipById(userId);
        if (membership != null) {
            long durationInDays = ChronoUnit.DAYS.between(membership.getStartDate(), LocalDate.now());
            int months = (int) Math.ceil(durationInDays / 30.0);
            return months == 0 ? 1 : months;
        }
        return 0;
    }
}
