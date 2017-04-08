package com.example.hppc.edified;

import java.util.HashMap;

/**
 * Created by hppc on 04-Jan-17.
 */

public class User {

    private String firstName, lastName, emailAddress, role;
    private HashMap<String, Course> enrolledCourses = new HashMap<>();

    User() {
        firstName = "";
        lastName = "";
        emailAddress = "";
        role = "";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public HashMap<String, Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(HashMap<String, Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }
}
