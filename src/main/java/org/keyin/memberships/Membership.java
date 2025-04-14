package org.keyin.memberships;

import java.time.LocalDate;

public class Membership {
    private int membershipId;
    private int userId;
    private LocalDate startDate;

    public Membership(int membershipId, int userId, LocalDate startDate) {
        this.membershipId = membershipId;
        this.userId = userId;
        this.startDate = startDate;
    }

    public Membership() {
    }

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Membership{" +
                "membershipId=" + membershipId +
                ", userId=" + userId +
                ", startDate=" + startDate +
                '}';
    }
}
