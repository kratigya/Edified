package com.example.hppc.edified;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by hppc on 12-Apr-17.
 */

public class EnrolledCourses extends Course {

    private Date enrollDate;
    private HashMap<Quiz, Integer> quizScore = new HashMap<>();

    EnrolledCourses() {

    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public HashMap<Quiz, Integer> getQuizScore() {
        return quizScore;
    }

    public void setQuizScore(HashMap<Quiz, Integer> quizScore) {
        this.quizScore = quizScore;
    }
}