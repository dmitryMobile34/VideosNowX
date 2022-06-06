package com.elysiumbetas.videosnowx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class VNXGP extends AppCompatActivity {

    private TextView naebkaTxt;
    private Button naebkaBtn;
    private LottieAnimationView loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vnxgp);

        ConstraintLayout constraintLayout = findViewById(R.id.mainlayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        final Handler handler = new Handler();

        naebkaTxt = findViewById(R.id.naebkaTxt);
        naebkaBtn = findViewById(R.id.naebkaBtn);
        loading = findViewById(R.id.loading);

        naebkaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setVisibility(View.VISIBLE);
                naebkaBtn.setClickable(false);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loading.setVisibility(View.INVISIBLE);
                        naebkaTxt.setVisibility(View.VISIBLE);
                    }
                }, 5000);
            }
        });

    }
}