package com.example.hppc.edified;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by hppc on 01-Jan-17.
 */

public class CourseHolder extends RecyclerView.ViewHolder {

    private TextView course_name, course_category;
    private Button enroll;
    private CourseHolder.ClickListener mClickListener;

    public CourseHolder(View itemView) {
        super(itemView);

        course_name = (TextView) itemView.findViewById(R.id.cname);
        course_category = (TextView) itemView.findViewById(R.id.ccategory);
        enroll = (Button) itemView.findViewById(R.id.enroll);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        });

        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onEnrollClick(v, getAdapterPosition());
            }
        });
    }

    public void setOnClickListener(CourseHolder.ClickListener clickListener) {
        mClickListener = clickListener;
    }

    public TextView getCourse_name() {
        return course_name;
    }

    public void setCourse_name(TextView course_name) {
        this.course_name = course_name;
    }

    public TextView getCourse_category() {
        return course_category;
    }

    public void setCourse_category(TextView course_category) {
        this.course_category = course_category;
    }

    public Button getEnroll() {
        return enroll;
    }

    public void setEnroll(Button enroll) {
        this.enroll = enroll;
    }

    //Interface to send callbacks...
    public interface ClickListener {
        public void onItemClick(View view, int position);

        public void onEnrollClick(View view, int position);
    }
}