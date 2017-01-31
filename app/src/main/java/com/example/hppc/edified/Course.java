package com.example.hppc.edified;

/**
 * Created by hppc on 02-Jan-17.
 */

public class Course implements FireBaseConn {

    private int courseID;
    private String courseName, courseCategory, courseDesc;

    void Course(String name, String category, String desc) {

    }

    void addCourse() {
        mDatabase.child("courses").child(this.getid()).setValue(this);
    }

    String getid() {
        return Integer.toString(courseID);
    }
}
