package com.example.hppc.edified;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

public class AddQuiz extends AppCompatActivity {

    private EditText name;
    private Button add;
    private Quiz newQuiz;
    private Course crs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        crs = getIntent().getExtras().getParcelable("course");

        name = (EditText) findViewById(R.id.quizName);
        add = (Button) findViewById(R.id.addQues);

        newQuiz = new Quiz();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Objects.equals(name.getText().toString(), "")) {
                    newQuiz.setQuizName(name.getText().toString());
                    Intent intent = new Intent(AddQuiz.this, AddQuestion.class);
                    intent.putExtra("quiz", (Parcelable) newQuiz);
                    intent.putExtra("course", (Parcelable) crs);
                    startActivity(intent);
                } else {
                    name.setError("Quiz Name Required");
                }
            }
        });
    }

}
