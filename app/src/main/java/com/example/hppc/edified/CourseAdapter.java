package com.example.hppc.edified;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hppc on 01-Jan-17.
 */

public class CourseAdapter extends RecyclerView.Adapter {

    String[] course = {"OOP with JAVA", "Computer Security", "Compiler Design"};
    String[] category = {"Computer Science", "Computer Science", "Computer Science"};
    Context context;

    public CourseAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.course_card, parent, false);
        return new CourseHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        CourseHolder courseHolder = (CourseHolder) holder;
        courseHolder.getCourse_name().setText(course[position]);
        courseHolder.getCourse_category().setText(category[position]);
        courseHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = ((MainActivity) context).getSupportFragmentManager().beginTransaction();
                QuizFragment quizFragment = new QuizFragment();
                fragmentTransaction.replace(R.id.fragment1, quizFragment, "QuizList");
                fragmentTransaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return course.length;
    }
}
