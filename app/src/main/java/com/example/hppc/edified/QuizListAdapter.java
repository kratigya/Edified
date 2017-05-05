package com.example.hppc.edified;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hppc on 24-Apr-17.
 */

public class QuizListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Quiz> quizList;
    private Quiz qz;
    private ArrayList<Question> quesList;
    private HashMap<String, Question> map;

    public QuizListAdapter(ArrayList<Quiz> quizList) {
        this.quizList = quizList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder vh;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.quiz_card, parent, false);
        vh = new QuizListHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        QuizListHolder vh = (QuizListHolder) holder;
        qz = quizList.get(position);
        map = qz.getQuestionMap();
        vh.getQuizNo().setText(qz.getQuizName());
        vh.getScore().setText(qz.getScore());
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getRandomColor();
        TextDrawable drawable = TextDrawable.builder().buildRound("Q" + (position + 1), color);
        vh.getImage().setImageDrawable(drawable);
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quesList = new ArrayList<>(map.values());
                Intent intent = new Intent(v.getContext(), QuizActivity.class);
                intent.putParcelableArrayListExtra("questionList", quesList);
                intent.putExtra("quesCount", qz.getQuesCount());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }
}
