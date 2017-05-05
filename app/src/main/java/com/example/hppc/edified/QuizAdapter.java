package com.example.hppc.edified;

import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hppc on 16-Apr-17.
 */

public class QuizAdapter extends BaseAdapter {

    private TextView question;
    private RadioGroup option;
    private RadioButton radioButton, op1, op2, op3, op4;
    private Button submit;
    private ArrayList<Question> questions;
    private FloatingActionButton fab;
    private ArrayList<String> answer = new ArrayList<>();
    private ArrayList<String> correct = new ArrayList<>();
    private ArrayList<Integer> res = new ArrayList<>();
    private View v;

    QuizAdapter(ArrayList<Question> questions) {
        this.questions = questions;
    }

    @Override
    public int getCount() {
        return questions.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        v = inflater.inflate(R.layout.quiz_question, parent, false);
        question = (TextView) v.findViewById(R.id.ques);
        option = (RadioGroup) v.findViewById(R.id.optionRadio);
        op1 = (RadioButton) v.findViewById(R.id.op1);
        op2 = (RadioButton) v.findViewById(R.id.op2);
        op3 = (RadioButton) v.findViewById(R.id.op3);
        op4 = (RadioButton) v.findViewById(R.id.op4);
        question.setText(questions.get(position).getQues());
        op1.setText(questions.get(position).getOptions().get(0));
        op2.setText(questions.get(position).getOptions().get(1));
        op3.setText(questions.get(position).getOptions().get(2));
        op4.setText(questions.get(position).getOptions().get(3));

        option.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                radioButton = (RadioButton) v.findViewById(checkedId);
                String str = "";
                switch (checkedId) {
                    case R.id.op1:
                        str = "A";
                        break;
                    case R.id.op2:
                        str = "B";
                        break;
                    case R.id.op3:
                        str = "C";
                        break;
                    case R.id.op4:
                        str = "D";
                        break;
                }

                answer.add(str);
                String temp = questions.get(position).getAnswer();
                correct.add(temp);
                if (str.equalsIgnoreCase(temp)) {
                    res.add(1);
                } else {
                    res.add(0);
                }
            }
        });

        return v;
    }

    public ArrayList<Integer> getRes() {
        return res;
    }

    public ArrayList<String> getAnswer() {
        return answer;
    }

    public ArrayList<String> getCorrect() {
        return correct;
    }
}