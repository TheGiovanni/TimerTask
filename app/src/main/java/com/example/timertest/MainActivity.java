package com.example.timertest;

import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {

    public long startTime = 0;
    public long stopTime = 0;
    public long nowTime = 0;


    private TextView timerText;
    private Button startButton;
    private Button stopButton;
    private Button resetButton;


    private Timer timer = null;
    private CountUpTimerTask countUpTimerTask = null;
    private final Handler handler = new Handler();
    private volatile boolean stopRun = false;

    private SimpleDateFormat dataFormat =
            new SimpleDateFormat("mm:ss.SS", Locale.JAPAN);

    private long diffTime;
    private int timerState = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerText = findViewById(R.id.timer);
        timerText.setText(dataFormat.format(0));

        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(this);

        stopButton = findViewById(R.id.stopButton);
        stopButton.setOnClickListener(this);
        resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(this);
        timer = new Timer();

    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public void stop() {
        stopTime += System.currentTimeMillis() - startTime;
    }

    public void reStart() {
        startTime = System.currentTimeMillis();
    }
    public void reset() {
        stopTime = 0;
    }

    public String getDispString() {
        nowTime =  System.currentTimeMillis() - (startTime - stopTime);
        long temp = nowTime;
        return dataFormat.format(nowTime);
    }
    @Override
    public void onClick(View v) {

        // TODO 自動生成されたメソッド・スタブ
        switch(v.getId()){
            case R.id.startButton:
                if(timerState == 0) {
                    startButton.setEnabled(false);
                    stopButton.setEnabled(true);
                    resetButton.setEnabled(false);
                    timerState = 1;
                    start();
                    countUpTimerTask = new CountUpTimerTask(this,this);
                    timer = new Timer(true);
                    timer.schedule(countUpTimerTask, 100, 100);
                }
                break;
            case R.id.stopButton:
                if(timer != null) {
                    if(timerState == 1){
                        startButton.setEnabled(true);
//                        stopButton.setEnabled(false);
                        resetButton.setEnabled(true);
                        timerState = 0;
                        timer.cancel();
                        timer = null;
                        stop();
                        return;
                    }

                }
                if(timerState == 0){
                    startButton.setEnabled(true);
                    stopButton.setEnabled(false);
                    resetButton.setEnabled(false);
                    startTime = 0;
                    stopTime = 0;
                    timerText.setText(dataFormat.format(0));
                }
                break;
            case R.id.resetButton:
                if(timerState == 0){
                    startButton.setEnabled(true);
                    stopButton.setEnabled(false);
                    resetButton.setEnabled(false);
                    startTime = 0;
                    stopTime = 0;
                    timerText.setText(dataFormat.format(0));
                }
                break;
            default:
        }
    }
}