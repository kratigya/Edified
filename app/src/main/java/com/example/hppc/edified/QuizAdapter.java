package com.example.hppc.edified;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.quiz_question, parent, false);
        question = (TextView) v.findViewById(R.id.ques);
        option = (RadioGroup) v.findViewById(R.id.optionRadio);
        op1 = (RadioButton) v.findViewById(R.id.op1);
        op2 = (RadioButton) v.findViewById(R.id.op2);
        op3 = (RadioButton) v.findViewById(R.id.op3);
        op4 = (RadioButton) v.findViewById(R.id.op4);
        question.setText(questions.get(position).getQues());
        op1.setText(questions.get(position).getOptions()[0]);
        op2.setText(questions.get(position).getOptions()[1]);
        op3.setText(questions.get(position).getOptions()[2]);
        op4.setText(questions.get(position).getOptions()[3]);

        return v;
    }
}
