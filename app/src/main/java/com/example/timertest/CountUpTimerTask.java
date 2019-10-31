package com.example.timertest;

import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.widget.TextView;

//タイマークラス
public class CountUpTimerTask extends TimerTask {

    private Handler handler;    //UIスレッドハンドラ
    private Context context;    //コンテキスト

    private MainActivity timeCtrl;

    //コンストラクタ
    public CountUpTimerTask(Context con, MainActivity timerController) {
        this.handler = new Handler();
        this.context = con;
        this.timeCtrl = timerController;
    }


    //メインループ処理
    @Override
    public void run() {
        handler.post(new Runnable() {
            public void run() {
                String a = timeCtrl.getDispString();
                TextView tv = (TextView) ((Activity) context).findViewById(R.id.timer);
                tv.setText(a);
            }
        });
    }
}
 