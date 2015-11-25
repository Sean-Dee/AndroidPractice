package com.dee.sean.counttime;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputText;
    private Button getTime, startTime, stopTime;
    private TextView time;
    private int i = 0;
    private Timer timer = null;
    private TimerTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        inputText = (EditText) findViewById(R.id.inputtime);
        getTime = (Button) findViewById(R.id.gettime);
        startTime = (Button) findViewById(R.id.starttime);
        stopTime = (Button) findViewById(R.id.stoptime);
        time = (TextView) findViewById(R.id.time);
        getTime.setOnClickListener(this);
        startTime.setOnClickListener(this);
        stopTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gettime:
                String str = inputText.getText().toString();
                time.setText(str);
                i = Integer.parseInt(str);
                break;
            case R.id.starttime:
                startTime();
                break;
            case R.id.stoptime:
                stopTime();
                break;
        }
    }

    private Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            time.setText(msg.arg1 + "");
            startTime();
        }
    };

    private void startTime() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                i--;
                if (i < 0) {
                    return;
                }
                Message msg = mHandler.obtainMessage();
                msg.arg1 = i;
                mHandler.sendMessage(msg);
            }
        };
        timer.schedule(task, 1000);
    }

    private void stopTime() {
        timer.cancel();
    }
}
