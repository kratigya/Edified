package com.example.hppc.edified;

/**
 * Created by hppc on 02-Jan-17.
 */

public class Course implements FireBaseConn {

    private static int courseID = 0;
    private String courseName, courseCategory, courseDesc;

    Course(String name, String category, String desc) {
        courseName = name;
        courseCategory = category;
        courseDesc = desc;
        courseID++;
    }

    public String getid() {
        return Integer.toString(courseID);
    }

    public void addCourse(){
        mDatabase.child("courses").child(getid()).setValue(this);
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
        this.courseID = courseID++;
    }

    public String getCourseCategory() {
        return courseCategory;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseDesc() {
        return courseDesc;
    }
}
