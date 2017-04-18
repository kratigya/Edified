package com.example.hppc.edified;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class CourseQuizActivity extends AppCompatActivity implements FireBaseConn {

    private RecyclerView quizRecyclerView;
    private RecyclerView.Adapter quizAdapter;
    private RecyclerView.LayoutManager quizLayoutManager;
    private ArrayList<Quiz> quizList;
    private Quiz qz;
    private ArrayList<Question> quesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        quizRecyclerView = (RecyclerView) findViewById(R.id.quizRecView);
        quizRecyclerView.setHasFixedSize(true);
        quizLayoutManager = new LinearLayoutManager(this);
        quizRecyclerView.setLayoutManager(quizLayoutManager);

        quizAdapter = new FirebaseRecyclerAdapter<Quiz, QuizListHolder>(
                Quiz.class,
                R.layout.quiz_card,
                QuizListHolder.class,
                mDatabase.child("courses").child("quizzes")) {

            @Override
            protected Quiz parseSnapshot(DataSnapshot snapshot) {
                Quiz quiz = super.parseSnapshot(snapshot);
                if (quiz != null) {
                    quiz.setQuizID(snapshot.getKey());
                    quizList.add(quiz);
                }
                return quiz;
            }

            @Override
            protected void populateViewHolder(QuizListHolder viewHolder, Quiz model, int position) {
                qz = quizList.get(position);
                quesList = qz.getQuestionArrayList();
                viewHolder.getQuizNo().setText(qz.getQuizName());
                viewHolder.getScore().setText(qz.getScore());
                ColorGenerator generator = ColorGenerator.MATERIAL;
                int color = generator.getRandomColor();
                TextDrawable drawable = TextDrawable.builder().buildRound("Q" + (position + 1), color);
                viewHolder.getImage().setImageDrawable(drawable);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(CourseQuizActivity.this, QuizActivity.class);
                        intent.putParcelableArrayListExtra("questionList", quesList);
                        startActivity(intent);
                    }
                });
            }
        };
        quizRecyclerView.setAdapter(quizAdapter);
    }
}
