package com.example.hppc.edified;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hppc on 05-Jan-17.
 */

public class QuizListHolder extends RecyclerView.ViewHolder {

    TextView quizNo, score;
    ImageView image;

    public QuizListHolder(View itemView) {
        super(itemView);

        quizNo = (TextView) itemView.findViewById(R.id.quiz_num);
        score = (TextView) itemView.findViewById(R.id.quiz_score);
        image = (ImageView) itemView.findViewById(R.id.img_view);
    }

    public TextView getQuizNo() {
        return quizNo;
    }

    public void setQuizNo(TextView quizNo) {
        this.quizNo = quizNo;
    }

    public TextView getScore() {
        return score;
    }

    public void setScore(TextView score) {
        this.score = score;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}
