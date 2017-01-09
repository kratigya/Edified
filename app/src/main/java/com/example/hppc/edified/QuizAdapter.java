package com.example.hppc.edified;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

/**
 * Created by hppc on 05-Jan-17.
 */

public class QuizAdapter extends RecyclerView.Adapter {

    String[] quizno = {"Quiz 1", "Quiz 2", "Quiz 3"};
    String[] score = {"10/10", "12/20", "17/20"};

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.quiz_card, parent, false);
        return new QuizHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        QuizHolder quizHolder = (QuizHolder) holder;
        quizHolder.getQuizNo().setText(quizno[position]);
        quizHolder.getScore().setText(score[position]);
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getRandomColor();
        TextDrawable drawable = TextDrawable.builder().buildRound("Q" + (position + 1), color);
        quizHolder.getImage().setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return quizno.length;
    }
}
