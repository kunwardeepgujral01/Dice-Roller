package com.example.diceroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Declaration of variables
    MaterialButton Mbtn;
    TextView textview;
    ImageView diceImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);

        //for full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //assigning of variables
        Mbtn = findViewById(R.id.btn_roll_dice);
        textview = findViewById(R.id.textView);
        diceImage = findViewById(R.id.diceImage);

        //mediaPlayer for dice button sound
        final MediaPlayer mp=MediaPlayer.create(this,R.raw.sound);

        //button pressed
        Mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //for generating random number between 1-6
                Random random = new Random();
                int score = random.nextInt(6) + 1; //0 is included and 6 is not so adding 1

                //for rotating the image
                startAnimation();

                //starting the audio
                mp.start();

                //thread for delaying the new image to show after 1s
                Handler handler=new Handler(Looper.myLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textview.setText(String.valueOf(score));

                        //for showing image according to score
                        switch (score) {
                            case 1:
                                diceImage.setImageResource(R.drawable.dice1);
                                break;
                            case 2:
                                diceImage.setImageResource(R.drawable.dice2);
                                break;
                            case 3:
                                diceImage.setImageResource(R.drawable.dice3);
                                break;
                            case 4:
                                diceImage.setImageResource(R.drawable.dice4);
                                break;
                            case 5:
                                diceImage.setImageResource(R.drawable.dice5);
                                break;
                            case 6:
                                diceImage.setImageResource(R.drawable.dice6);
                                break;
                        }
                    }
                },1000);

            }

        });

    }
    public void startAnimation(){
        ObjectAnimator animator=ObjectAnimator.ofFloat(diceImage,"rotation",0f,360f);
        animator.setDuration(1000);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(animator);
        animatorSet.start();
    }

}
