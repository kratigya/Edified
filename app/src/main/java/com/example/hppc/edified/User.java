package com.example.hppc.edified;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by hppc on 04-Jan-17.
 */

class User implements Serializable, Parcelable {

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String role;
    private HashMap<String, Course> enrolledCourses = new HashMap<>();

    User() {
        firstName = "";
        lastName = "";
        emailAddress = "";
        role = "";
    }

    protected User(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        emailAddress = in.readString();
        role = in.readString();
        enrolledCourses = in.readHashMap(Course.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(emailAddress);
        dest.writeString(role);
        dest.writeMap(enrolledCourses);
    }

    @Override
    public int describeContents() {
        return 0;
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
