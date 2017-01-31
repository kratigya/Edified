package com.example.hppc.edified;

/**
 * Created by hppc on 02-Jan-17.
 */

public class Course implements FireBaseConn {

    private static int courseID = 0;
    private String courseName, courseCategory, courseDesc;

    void Course(String name, String category, String desc) {
        this.setCourseCategory(category);
        this.setCourseDesc(desc);
        this.setCourseName(name);
        courseID++;
        this.setCourseID();
    }

    void addCourse() {
        mDatabase.child("courses").child(this.getid()).setValue(this);
    }

    String getid() {
        return Integer.toString(courseID);
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public void setCourseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
    }

    public void setCourseID() {
        this.courseID = courseID;
    }
}
