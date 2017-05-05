package com.example.hppc.edified;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by hppc on 12-Apr-17.
 */

public class Question implements Serializable, Parcelable {

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
    private String ques;
    private String answer;
    private ArrayList<String> options = new ArrayList<>();

    Question() {

    }

    protected Question(Parcel in) {
        ques = in.readString();
        answer = in.readString();
        options = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ques);
        dest.writeString(answer);
        dest.writeStringList(options);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
