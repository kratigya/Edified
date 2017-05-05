package com.example.hppc.edified;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterViewFlipper;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    private static final String TAG = "QuizActivity";
    private ArrayList<Question> questionArrayList;
    private QuizAdapter quizAdapter;
    private int size;
    private AdapterViewFlipper adapterViewFlipper;
    private ArrayList<String> answer, correct;
    private ArrayList<Integer> res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        questionArrayList = intent.getParcelableArrayListExtra("questionList");
        size = intent.getExtras().getInt("quesCount");

        quizAdapter = new QuizAdapter(questionArrayList);

        adapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.quizFliper);
        adapterViewFlipper.setAdapter(quizAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.next_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int displayedChild = adapterViewFlipper.getDisplayedChild();
                if (displayedChild == size - 1) {
                    Log.v(TAG, "Hellooo results");
                    res = quizAdapter.getRes();
                    answer = quizAdapter.getAnswer();
                    correct = quizAdapter.getCorrect();
                    Intent intent = new Intent(QuizActivity.this, Results.class);
                    intent.putStringArrayListExtra("answer", answer);
                    intent.putIntegerArrayListExtra("result", res);
                    intent.putStringArrayListExtra("correct", correct);
                    intent.putExtra("count", size);
                    startActivity(intent);
                }
                adapterViewFlipper.showNext();
            }
        });
    }
}
