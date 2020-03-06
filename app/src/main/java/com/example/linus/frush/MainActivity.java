package com.example.linus.frush;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

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

        findViewById(R.id.plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddCardActivivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
                
            }
        });

        findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCardActivivity.class);
                intent.putExtra("stringKey1", ((TextView)findViewById(R.id.flashcardquestion)).getText());
                intent.putExtra("stringKey2", ((TextView) findViewById(R.id.choiceC)).getText());
                intent.putExtra("stringKey3", ((TextView) findViewById(R.id.choiceA)).getText());
                intent.putExtra("stringKey4", ((TextView) findViewById(R.id.choiceB)).getText());

                MainActivity.this.startActivityForResult(intent, 100);
            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 100 && resultCode == RESULT_OK) {
            String string1 = data.getExtras().getString("string1");
            String string2 = data.getExtras().getString("string2");
            String string3 = data.getExtras().getString("string3");
            String string4 = data.getExtras().getString("string4");
            ((TextView) findViewById(R.id.flashcardquestion)).setText(string1);
            ((TextView) findViewById(R.id.choiceC)).setText(string2);
            ((TextView) findViewById(R.id.choiceA)).setText(string3);
            ((TextView) findViewById(R.id.choiceB)).setText(string4);

            Snackbar.make(findViewById(R.id.flashcardquestion),
                    "Card created succesfully!",
                    Snackbar.LENGTH_SHORT)
                    .show();
        }

    }

}
