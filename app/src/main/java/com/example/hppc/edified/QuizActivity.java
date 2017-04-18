package com.example.hppc.edified;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterViewFlipper;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    private ArrayList<Question> questionArrayList;
    private QuizAdapter quizAdapter;
    private AdapterViewFlipper adapterViewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        questionArrayList = intent.getParcelableArrayListExtra("questionList");

        quizAdapter = new QuizAdapter(questionArrayList);

        adapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.quizFliper);
        adapterViewFlipper.setAdapter(quizAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.next_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterViewFlipper.showNext();
            }
        });
    }

}
