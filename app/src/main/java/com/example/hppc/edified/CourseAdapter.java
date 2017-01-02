package com.example.hppc.edified;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hppc on 01-Jan-17.
 */

public class CourseAdapter extends RecyclerView.Adapter {

    String[] course = {"OOP with JAVA", "Computer Secutiry", "Compiler Design"};
    String[] category = {"Computer Science", "Computer Science", "Computer Science"};

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
        courseHolder.getCourse_category().setText(course[position]);

    }

    @Override
    public int getItemCount() {
        return course.length;
    }
}
