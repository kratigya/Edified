package com.example.hppc.edified;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by hppc on 05-Jan-17.
 */

public class Quiz implements Serializable, Parcelable {

    public static final Creator<Quiz> CREATOR = new Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel in) {
            return new Quiz(in);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };
    private String quizName, score, quizID;
    private int quesCount;
    private HashMap<String, Question> questionMap;

    Quiz() {

    }

    protected Quiz(Parcel in) {
        quizName = in.readString();
        score = in.readString();
        quizID = in.readString();
        quesCount = in.readInt();
        questionMap = in.readHashMap(Question.class.getClassLoader());
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public HashMap<String, Question> getQuestionMap() {
        return questionMap;
    }

    public void setQuestionMap(HashMap<String, Question> questionMap) {
        this.questionMap = questionMap;
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(quizName);
        dest.writeString(score);
        dest.writeString(quizID);
        dest.writeInt(quesCount);
        dest.writeMap(questionMap);
    }

    public int getQuesCount() {
        return quesCount;
    }

    public void setQuesCount(int quesCount) {
        this.quesCount = quesCount;
    }
}