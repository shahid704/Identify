package com.example.mlseriesdemonstrator.helpers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.mlseriesdemonstrator.MainActivity;
import com.example.mlseriesdemonstrator.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //for delaying the spash screen
        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep(4500);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }

            }

        }; thread.start();
    }
}