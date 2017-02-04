package com.example.hppc.edified;

/**
 * Created by hppc on 02-Jan-17.
 */

public class Course implements FireBaseConn {

    private String courseID, courseName, courseCategory, courseDesc;
    private String COURSE_CHILD = "courses";

    Course() {

    }

    Course(String name, String category, String desc) {
        courseName = name;
        courseCategory = category;
        courseDesc = desc;
    }

    public void addCourse() {
        mDatabase.child(COURSE_CHILD).push().setValue(this);
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }
}
