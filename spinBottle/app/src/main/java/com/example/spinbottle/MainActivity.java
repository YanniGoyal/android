package com.example.spinbottle;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView bot;
    private Random rand = new Random();
    private int lstDr;
    private boolean spn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bot = findViewById(R.id.bottle);
        EditText inputText = findViewById(R.id.inputText);
        bot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!spn) {
                    int num;
                    // generate a random number from 1-1800
                    if(inputText.getText().toString().equals("")) {
                        num = rand.nextInt(1800);
                    } else {
                        int random_num = Integer.parseInt(inputText.getText().toString());
                        num = rand.nextInt(random_num);
                    }

                    // set the pivot to the centre of the image
                    float pX = bot.getWidth() / 2;
                    float pY = bot.getHeight() / 2;

                    // pass parameters in RoatateAnimation function
                    Animation rot = new RotateAnimation(lstDr, num, pX, pY);

                    // set rotate duration 2500 millisecs
                    rot.setDuration(2500);

                    // rotation will persist after finishing
                    rot.setFillAfter(true);
                    rot.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            spn = true;
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            spn = false;
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });

                    // change the last direction
                    lstDr = num;

                    // start the animation
                    bot.startAnimation(rot);
                }
            }
        });
    }

}
