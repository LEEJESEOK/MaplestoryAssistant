package com.android.maplestoryassistant;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView currentTimeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentTimeTextView = findViewById(R.id.CurrentTimeTextView);

        Thread currentTimeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!Thread.currentThread().isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateCurrentTime();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        currentTimeThread.start();
    }

    private void updateCurrentTime() {
        Calendar calendar = Calendar.getInstance();

        String currentTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault()).format(calendar.getTime());
        currentTimeTextView.setText(currentTime);
    }
}
