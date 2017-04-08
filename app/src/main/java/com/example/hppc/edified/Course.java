package com.example.hppc.edified;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by hppc on 02-Jan-17.
 */

public class Course implements FireBaseConn, Parcelable, Serializable {

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };
    private String courseID, courseName, courseCategory, courseDesc, courseBook;
    private String COURSE_CHILD = "courses";

    Course() {

    }

    Course(String name, String category, String desc) {
        courseName = name;
        courseCategory = category;
        courseDesc = desc;
    }

    protected Course(Parcel in) {
        courseID = in.readString();
        courseName = in.readString();
        courseCategory = in.readString();
        courseDesc = in.readString();
        courseBook = in.readString();
        COURSE_CHILD = in.readString();
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

    public String getCourseBook() {
        return courseBook;
    }

    public void setCourseBook(String courseBook) {
        this.courseBook = courseBook;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(courseID);
        dest.writeString(courseName);
        dest.writeString(courseCategory);
        dest.writeString(courseDesc);
        dest.writeString(courseBook);
    }
}
