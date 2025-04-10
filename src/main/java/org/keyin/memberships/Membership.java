package org.keyin.memberships;

// Membership class //

public class Membership{

// Attributtes //
private String memberName;
private String membershipId;
private double membershipCost;
private String startDate;
private int duration; // In Months //
private String memberType; 

// Constructor //

public Membership(String memberName, String membershipId, double membershipCost, String startDate, int duration, String memberType, String status) {
    this.memberName = memberName;
    this.membershipId = membershipId;
    this.membershipCost = membershipCost;
    this.startDate = startDate;
    this.duration = duration;
    this.memberType = memberType;

}

// No Argument Constructor //
public Membership(){

}

// Getters //
public String getMemberName() {
    return memberName;
}

public String getMembershipId() {
    return membershipId;
}
public double getMembershipCost() {
    return membershipCost;
}
public String getStartDate() {
    return startDate;
}
public int getDuration() {
    return duration;
}
public String getMemberType() {
    return memberType;
}


// Setters //

public void setMemberName(String memberName) {
    this.memberName = memberName;
}

public void setMembershipId(String membershipId) {
    this.membershipId = membershipId;
}

public void setMembershipCost(double membershipCost) {
    this.membershipCost = membershipCost;
}

public void setStartDate(String startDate) {
    this.startDate = startDate;
}

public void setDuration(int duration) {
    this.duration = duration;
}

public void setMemberType(String memberType) {
    this.memberType = memberType;
}

// toString method //
@Override
    public String toString() {
        return "Membership{" +
                "memberName='" + memberName + '\'' +
                ", membershipId='" + membershipId + '\'' +
                ", membershipCost=" + membershipCost +
                ", startDate='" + startDate + '\'' +
                ", duration=" + duration +
                ", memberType='" + memberType + '\'' +
                '}';
    }

}
