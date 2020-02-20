package com.example.linus.frush;

import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    boolean isShowingAnswers = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.choiceA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isShowingAnswers) {
                    findViewById(R.id.choiceA).setBackground(getResources().getDrawable(R.drawable.wrong_answer_color_shape));
                    findViewById(R.id.choiceC).setBackground(getResources().getDrawable(R.drawable.answer_color_shape));
                }
            }
        });

        findViewById(R.id.choiceB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isShowingAnswers) {
                    findViewById(R.id.choiceB).setBackground(getResources().getDrawable(R.drawable.wrong_answer_color_shape));
                    findViewById(R.id.choiceC).setBackground(getResources().getDrawable(R.drawable.answer_color_shape));
                }
            }
        });

        findViewById(R.id.choiceC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isShowingAnswers) {
                    findViewById(R.id.choiceC).setBackground(getResources().getDrawable(R.drawable.answer_color_shape));
                }
            }
        });
        findViewById(R.id.root_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isShowingAnswers) {
                    findViewById(R.id.choiceA).setBackground(getResources().getDrawable(R.drawable.choices_color_shape));
                    findViewById(R.id.choiceC).setBackground(getResources().getDrawable(R.drawable.choices_color_shape));
                    findViewById(R.id.choiceB).setBackground(getResources().getDrawable(R.drawable.choices_color_shape));
                }
            }
        });
        findViewById(R.id.hide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isShowingAnswers){
                    ((ImageView)findViewById(R.id.hide)).setImageResource(R.drawable.ic_show);
                    isShowingAnswers = true;
                    findViewById(R.id.choiceA).setVisibility(View.VISIBLE);
                    findViewById(R.id.choiceB).setVisibility(View.VISIBLE);
                    findViewById(R.id.choiceC).setVisibility(View.VISIBLE);

                }else{
                    ((ImageView)findViewById(R.id.hide)).setImageResource(R.drawable.ic_hide);
                    isShowingAnswers = false;
                    findViewById(R.id.choiceA).setVisibility(View.INVISIBLE);
                    findViewById(R.id.choiceB).setVisibility(View.INVISIBLE);
                    findViewById(R.id.choiceC).setVisibility(View.INVISIBLE);
                    findViewById(R.id.choiceA).setBackground(getResources().getDrawable(R.drawable.choices_color_shape));
                    findViewById(R.id.choiceC).setBackground(getResources().getDrawable(R.drawable.choices_color_shape));
                    findViewById(R.id.choiceB).setBackground(getResources().getDrawable(R.drawable.choices_color_shape));
                }

            }
        });


    }


}
