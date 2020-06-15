package com.example.hanaiabeauty.spilash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.hanaiabeauty.R;

import java.util.Timer;
import java.util.TimerTask;

public class SpilashActivity extends AppCompatActivity {
    private static final int WAIT_TIME =3000;
    private Timer waitTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spilash);

        waitTimer = new Timer();
        //Check is login

        waitTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                SpilashActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        startActivity(new Intent(SpilashActivity.this, MainActivity.class));
                        finish();

                    }
                });
            }
        }, WAIT_TIME);
    }

}
