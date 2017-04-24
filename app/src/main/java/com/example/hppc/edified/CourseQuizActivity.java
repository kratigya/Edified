package com.example.hppc.edified;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CourseQuizActivity extends AppCompatActivity implements FireBaseConn {

    private static final String TAG = "CourseQuizActivity";
    private RecyclerView quizRecyclerView;
    private RecyclerView.Adapter quizAdapter;
    private RecyclerView.LayoutManager quizLayoutManager;
    private ArrayList<Quiz> quizList;
    private Quiz qz;
    private ArrayList<Question> quesList;
    private FloatingActionButton addFab;
    private Course crs;
    private HashMap<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        crs = getIntent().getExtras().getParcelable("course");
        quizList = new ArrayList<>();

        quizRecyclerView = (RecyclerView) findViewById(R.id.quizRecView);
        quizRecyclerView.setHasFixedSize(true);
        quizLayoutManager = new LinearLayoutManager(this);
        quizRecyclerView.setLayoutManager(quizLayoutManager);

        addFab = (FloatingActionButton) findViewById(R.id.addFab);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseQuizActivity.this, AddQuiz.class);
                intent.putExtra("course", (Parcelable) crs);
                startActivity(intent);
            }
        });

        map = crs.getQuizzes();

        for (Object o : map.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            mDatabase.child("quizzes").child(pair.getValue().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Quiz quiz = dataSnapshot.getValue(Quiz.class);
                    Log.v(TAG, quiz.getQuizName());
                    quizList.add(quiz);
                    quizAdapter = new QuizListAdapter(quizList);
                    quizRecyclerView.setAdapter(quizAdapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}
