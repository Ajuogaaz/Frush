package com.example.linus.frush;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.plattysoft.leonids.ParticleSystem;

import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    boolean isShowingAnswers = false;
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;
    Flashcard cardToEdit;
    private CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countDownTimer = new CountDownTimer(16000, 1000) {
            public void onTick(long millisUntilFinished) {
                ((TextView) findViewById(R.id.timer)).setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
            }
        };


        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcardquestion)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.choiceC)).setText(allFlashcards.get(0).getAnswer());
            ((TextView) findViewById(R.id.choiceA)).setText(allFlashcards.get(0).getWrongAnswer1());
            ((TextView) findViewById(R.id.choiceB)).setText(allFlashcards.get(0).getWrongAnswer2());
        }

        findViewById(R.id.choiceA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShowingAnswers) {
                    findViewById(R.id.choiceA).setBackground(getResources().getDrawable(R.drawable.wrong_answer_color_shape));
                    findViewById(R.id.choiceC).setBackground(getResources().getDrawable(R.drawable.answer_color_shape));
                }
            }
        });

        findViewById(R.id.choiceB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShowingAnswers) {
                    findViewById(R.id.choiceB).setBackground(getResources().getDrawable(R.drawable.wrong_answer_color_shape));
                    findViewById(R.id.choiceC).setBackground(getResources().getDrawable(R.drawable.answer_color_shape));
                }
            }
        });

        findViewById(R.id.choiceC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShowingAnswers) {
                    findViewById(R.id.choiceC).setBackground(getResources().getDrawable(R.drawable.answer_color_shape));
                    new ParticleSystem(MainActivity.this, 100, R.drawable.confetti, 3000)
                            .setSpeedRange(0.2f, 0.5f)
                            .oneShot(findViewById(R.id.choiceC), 100);
                }
            }
        });
        findViewById(R.id.root_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShowingAnswers) {
                    findViewById(R.id.choiceA).setBackground(getResources().getDrawable(R.drawable.choices_color_shape));
                    findViewById(R.id.choiceC).setBackground(getResources().getDrawable(R.drawable.choices_color_shape));
                    findViewById(R.id.choiceB).setBackground(getResources().getDrawable(R.drawable.choices_color_shape));
                }
            }
        });
        findViewById(R.id.hide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isShowingAnswers) {
                    ((ImageView) findViewById(R.id.hide)).setImageResource(R.drawable.ic_show);
                    isShowingAnswers = true;
                    findViewById(R.id.nextThing).setVisibility(View.INVISIBLE);
                    findViewById(R.id.choiceA).setVisibility(View.VISIBLE);
                    showAnswerAnimation(findViewById(R.id.choiceA));
                    findViewById(R.id.choiceB).setVisibility(View.VISIBLE);
                    showAnswerAnimation(findViewById(R.id.choiceB));
                    findViewById(R.id.choiceC).setVisibility(View.VISIBLE);
                    showAnswerAnimation(findViewById(R.id.choiceC));
                    findViewById(R.id.trash).setVisibility(View.INVISIBLE);

                } else {
                    ((ImageView) findViewById(R.id.hide)).setImageResource(R.drawable.ic_hide);
                    isShowingAnswers = false;
                    findViewById(R.id.nextThing).setVisibility(View.VISIBLE);
                    findViewById(R.id.choiceA).setVisibility(View.INVISIBLE);
                    findViewById(R.id.choiceB).setVisibility(View.INVISIBLE);
                    findViewById(R.id.choiceC).setVisibility(View.INVISIBLE);
                    findViewById(R.id.trash).setVisibility(View.VISIBLE);
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
                overridePendingTransition(R.anim.right_in, R.anim.out_left);

            }
        });

        findViewById(R.id.trash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isShowingAnswers) {
                    flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcardquestion)).getText().toString());
                    allFlashcards = flashcardDatabase.getAllCards();
                    if (!allFlashcards.isEmpty()) {
                        currentCardDisplayedIndex = getRandomNumber(0, allFlashcards.size() - 1);
                        ((TextView) findViewById(R.id.flashcardquestion)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                        ((TextView) findViewById(R.id.choiceC)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                        ((TextView) findViewById(R.id.choiceA)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
                        ((TextView) findViewById(R.id.choiceB)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
                    } else {

                        ((TextView) findViewById(R.id.flashcardquestion)).setText("Empty!!");
                        ((TextView) findViewById(R.id.choiceC)).setText("Add answer!");
                        ((TextView) findViewById(R.id.choiceA)).setText("Add answer!");
                        ((TextView) findViewById(R.id.choiceB)).setText("Add answer!");
                    }
                }
            }
        });

        findViewById(R.id.nextThing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isShowingAnswers && !allFlashcards.isEmpty()) {
                    currentCardDisplayedIndex = getRandomNumber(0, allFlashcards.size() - 1);

                    final Animation leftOutAnim = AnimationUtils.loadAnimation(view.getContext(), R.anim.out_left);
                    final Animation rightInAnim = AnimationUtils.loadAnimation(view.getContext(), R.anim.right_in);

                    leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            findViewById(R.id.timer).setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            ((TextView) findViewById(R.id.flashcardquestion)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                            ((TextView) findViewById(R.id.choiceC)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                            ((TextView) findViewById(R.id.choiceA)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
                            ((TextView) findViewById(R.id.choiceB)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
                            findViewById(R.id.flashcardquestion).startAnimation(rightInAnim);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    rightInAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            findViewById(R.id.timer).setVisibility(View.VISIBLE);
                            startTimer();

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    findViewById(R.id.flashcardquestion).startAnimation(leftOutAnim);
                }
            }
        });

        findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCardActivivity.class);
                intent.putExtra("stringKey1", ((TextView) findViewById(R.id.flashcardquestion)).getText());
                intent.putExtra("stringKey2", ((TextView) findViewById(R.id.choiceC)).getText());
                intent.putExtra("stringKey3", ((TextView) findViewById(R.id.choiceA)).getText());
                intent.putExtra("stringKey4", ((TextView) findViewById(R.id.choiceB)).getText());

                for (Flashcard card : allFlashcards) {
                    if (card.getQuestion() == ((TextView) findViewById(R.id.flashcardquestion)).getText()) {
                        cardToEdit = card;
                    }
                }

                MainActivity.this.startActivityForResult(intent, 1000);
                overridePendingTransition(R.anim.right_in, R.anim.out_left);
            }
        });
    }


    public int getRandomNumber(int minNumber, int maxNumber) {
        Random rand = new Random();
        return rand.nextInt((maxNumber - minNumber) + 1) + minNumber;
    }

    private void showAnswerAnimation(View answerSideView)
    {
        // get the center of the clipping circle
        int cx = answerSideView.getWidth() / 2;
        int cy = answerSideView.getHeight() / 2;

        //get final radius of the clipping circle
        float finalRadius = (float)Math.hypot(cx, cy);

        ///Create an Animation for the view
        Animator slideAnim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy,
                0f, finalRadius);
        slideAnim.setDuration(3000);
        slideAnim.start();

    }
    private void startTimer(){
        countDownTimer.cancel();
        countDownTimer.start();
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

            flashcardDatabase.insertCard(new Flashcard(string1, string2, string3, string4));
            allFlashcards = flashcardDatabase.getAllCards();

            Snackbar.make(findViewById(R.id.flashcardquestion),
                    "Card created successfully!",
                    Snackbar.LENGTH_SHORT)
                    .show();
        }
        if(requestCode == 1000 && resultCode == RESULT_OK) {
            String string1 = data.getExtras().getString("string1");
            String string2 = data.getExtras().getString("string2");
            String string3 = data.getExtras().getString("string3");
            String string4 = data.getExtras().getString("string4");
            ((TextView) findViewById(R.id.flashcardquestion)).setText(string1);
            ((TextView) findViewById(R.id.choiceC)).setText(string2);
            ((TextView) findViewById(R.id.choiceA)).setText(string3);
            ((TextView) findViewById(R.id.choiceB)).setText(string4);

            cardToEdit.setQuestion(string1);
            cardToEdit.setAnswer(string2);
            cardToEdit.setWrongAnswer1(string3);
            cardToEdit.setWrongAnswer2(string4);

            flashcardDatabase.updateCard(cardToEdit);
            allFlashcards = flashcardDatabase.getAllCards();

            Snackbar.make(findViewById(R.id.flashcardquestion),
                    "Card edited successfully!",
                    Snackbar.LENGTH_SHORT)
                    .show();
        }

    }

}
