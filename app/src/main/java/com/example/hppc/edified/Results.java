package com.example.hppc.edified;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class Results extends AppCompatActivity {

    private TableLayout tb;
    private ArrayList<String> answer, correct;
    private ArrayList<Integer> res;
    private int count;

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent rec_intent = getIntent();
        res = rec_intent.getIntegerArrayListExtra("result");
        answer = rec_intent.getStringArrayListExtra("answer");
        count = rec_intent.getExtras().getInt("count");
        correct = rec_intent.getStringArrayListExtra("correct");

        tb = (TableLayout) findViewById(R.id.resultTable);
        TableRow tbrow = new TableRow(this);
        tbrow.setWeightSum(3);
        TextView tv1 = new TextView(this);
        tv1.setText("Question");
        tv1.setGravity(Gravity.CENTER);
        tv1.setTextAppearance(R.style.TextAppearance_AppCompat_Medium);
        tbrow.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText("Your Answer");
        tv2.setGravity(Gravity.CENTER);
        tv2.setTextAppearance(R.style.TextAppearance_AppCompat_Medium);
        tbrow.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText("Correct Answer");
        tv3.setGravity(Gravity.CENTER);
        tv3.setTextAppearance(R.style.TextAppearance_AppCompat_Medium);
        tbrow.addView(tv3);
        tb.addView(tbrow);

        for (int i = 0; i < count; i++) {
            TableRow tbrow1 = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText("" + (i + 1));
            t1v.setGravity(Gravity.CENTER);
            tbrow1.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText(answer.get(i));
            t2v.setGravity(Gravity.CENTER);
            tbrow1.addView(t2v);
            TextView t3v = new TextView(this);
            t3v.setText(correct.get(i));
            t3v.setGravity(Gravity.CENTER);
            tbrow1.addView(t3v);
            if (res.get(i) == 1) {
                tbrow1.setBackgroundColor(Color.GREEN);
            } else {
                tbrow1.setBackgroundColor(Color.RED);
            }
            tb.addView(tbrow1);
        }
    }
}
