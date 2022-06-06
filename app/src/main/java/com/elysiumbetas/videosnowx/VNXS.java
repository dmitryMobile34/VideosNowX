package com.elysiumbetas.videosnowx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VNXS extends AppCompatActivity {

    EditText userEdit;
    Button saveNameBtn, startBtn;
    TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vnxs);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        userEdit = findViewById(R.id.namEdit);
        saveNameBtn = findViewById(R.id.userNameSaveButton);
        startBtn = findViewById(R.id.startUserBtn);
        welcomeText = findViewById(R.id.welcomeText);


        ConstraintLayout constraintLayout = findViewById(R.id.startlayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        SharedPreferences headPref = getSharedPreferences("HeadUserPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = headPref.edit();

        saveNameBtn.setOnClickListener(v -> {
            String edited = VNXS.this.userEdit.getText().toString();
            if (edited.isEmpty()) {
                Toast.makeText(this, "User name cannot be empty!", Toast.LENGTH_SHORT).show();
            } else {
                editor.putString("UserName", edited);
                editor.apply();
                saveNameBtn.setClickable(false);
                welcomeText.setText("Welcome to VideosNowX, " + edited);
                welcomeText.setVisibility(View.VISIBLE);
                startBtn.setVisibility(View.VISIBLE);
            }
        });
    }

    public void moveOnToStart(View view) {
        startActivity(new Intent(this, VNXGP.class));
        finish();

    }
}