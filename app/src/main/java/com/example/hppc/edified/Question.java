package com.example.hppc.edified;

import java.io.Serializable;

/**
 * Created by hppc on 12-Apr-17.
 */

public class Question implements Serializable {

    private String ques;
    private String answer;
    private String options[] = new String[4];

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
