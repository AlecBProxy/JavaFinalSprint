package org.keyin.memberships;

import java.util.List;

public class MembershipService {

    private final MembershipDAO membershipDAO;

    // Default constructor to internally creata a DAO //
    public MembershipService() {
        this.membershipDAO = new MembershipDAO();
    }

    // Constructor to inject a DAO for mocking purposes//

    public MembershipService(MembershipDAO membershipDAO) {
        this.membershipDAO = membershipDAO;
    }

    // Method to create a new Membership //
    public boolean createMembership(Membership membership) {
        // Example validation
        if (membership.getMembershipId() == null || membership.getMembershipId().isEmpty()) {
            System.out.println("Membership ID cannot be empty.");
            return false;
        }
        if (membership.getMemberName() == null || membership.getMemberName().isEmpty()) {
            System.out.println("Member name cannot be empty.");
            return false;
        }
        if (membership.getMembershipCost() < 0) {
            System.out.println("Membership cost cannot be negative.");
            return false;
        }

        // Check if membership already exists //
        Membership existingMembership = membershipDAO.getMembershipById(membership.getMembershipId());
        if (existingMembership != null) {
            System.out.println("Membership with ID " + membership.getMembershipId() + " already exists.");
            return false;
        }

        // If we pass the checks, create the membership //
        membershipDAO.createMembership(membership);
        return true;
    }

    // Retrieves a membership by ID //

    public Membership getMembership(String membershipId) {
        if (membershipId == null || membershipId.isEmpty()) {
            System.out.println("Invalid membership ID.");
            return null;
        }
        return membershipDAO.getMembershipById(membershipId);
    }

    // Retrieves all memberships //
    public List<Membership> getAllMemberships() {
        return membershipDAO.getAllMemberships();
    }

    // Updates a membership //

    public boolean updateMembership(Membership membership) {
        // Checks if membership already exists //
        Membership existing = membershipDAO.getMembershipById(membership.getMembershipId());
        if (existing == null) {
            System.out.println("Membership with ID " + membership.getMembershipId() + " not found.");
            return false;
        }

        // Cost must be greater than 0. //
        if (membership.getMembershipCost() < 0) {
            System.out.println("Membership cost cannot be negative.");
            return false;
        }

        membershipDAO.updateMembership(membership);
        return true;
    }

    // Deletes a membership //

    public boolean deleteMembership(String membershipId) {
        Membership existing = membershipDAO.getMembershipById(membershipId);
        if (existing == null) {
            System.out.println("Membership with ID " + membershipId + " not found.");
            return false;
        }
        membershipDAO.deleteMembership(membershipId);
        return true;
    }

    // Method to purchase a gym membership //
    public boolean purchaseMembership(String Name, String membershipType, double membershipCost, String startDate) {

        // Validate inputs //
        if (Name == null || Name.isEmpty()) {
            System.out.println("Member name cannot be empty.");
            return false;
        }
        if (membershipType == null || membershipType.isEmpty()) {
            System.out.println("Membership type cannot be empty.");
            return false;
        }
        if (membershipCost < 0) {
            System.out.println("Membership cost cannot be negative.");
            return false;
        }
        if (startDate == null || startDate.isEmpty()) {
            System.out.println("Start date cannot be empty.");
            return false;
        }

        // Create a new membership object //
        Membership membership = new Membership();
        membership.setMemberName(Name);
        membership.setMembershipId(membershipType);
        membership.setMembershipCost(membershipCost);
        membership.setStartDate(startDate);

        return createMembership(membership);

    }

}
