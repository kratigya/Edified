package com.example.hppc.edified;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddQuestion extends AppCompatActivity implements FireBaseConn {

    private EditText ques, opt1, opt2, opt3, opt4, ans;
    private Button submit, another;
    private Question newQues;
    private String[] options;
    private ArrayList<Question> questionArrayList;
    private Quiz quiz;
    private Course crs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        quiz = getIntent().getExtras().getParcelable("quiz");
        crs = getIntent().getExtras().getParcelable("course");

        questionArrayList = new ArrayList<>();
        ques = (EditText) findViewById(R.id.question);
        opt1 = (EditText) findViewById(R.id.option1);
        opt2 = (EditText) findViewById(R.id.option2);
        opt3 = (EditText) findViewById(R.id.option3);
        opt4 = (EditText) findViewById(R.id.option4);
        ans = (EditText) findViewById(R.id.correct);

        another = (Button) findViewById(R.id.addAnother);
        submit = (Button) findViewById(R.id.done);

        another.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newQues.setQues(ques.getText().toString());
                options = new String[4];
                options[1] = opt1.getText().toString();
                options[2] = opt2.getText().toString();
                options[3] = opt3.getText().toString();
                options[4] = opt4.getText().toString();
                newQues.setOptions(options);
                newQues.setAnswer(ans.getText().toString());
                questionArrayList.add(newQues);
                quiz.setQuestionArrayList(questionArrayList);
                resetViews();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = mDatabase.child("quizzes").push().getKey();
                mDatabase.child("quizzes").child(key).setValue(quiz);
                mDatabase.child("courses").child(crs.getCourseID()).child("quizzes").child(key).child("questionList").setValue(questionArrayList);
                mDatabase.child("courses").child(crs.getCourseID()).child("quizzes").child(key).setValue(key);
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private void resetViews() {
        ques.getText().clear();
        opt1.getText().clear();
        opt2.getText().clear();
        opt3.getText().clear();
        opt4.getText().clear();
        ans.getText().clear();
    }

}
