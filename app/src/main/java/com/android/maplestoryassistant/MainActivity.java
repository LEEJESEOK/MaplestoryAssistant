package com.android.maplestoryassistant;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TimePicker alarmTimePicker;
    Button applyButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        alarmTimePicker = findViewById(R.id.AlarmTimePicker);
        alarmTimePicker.setIs24HourView(true);

        SharedPreferences sharedPreferences = getSharedPreferences("alarm", MODE_PRIVATE);
        long millis = sharedPreferences.getLong("nextNotifyTime", Calendar.getInstance().getTimeInMillis());

        Calendar nextNotifyTime = new GregorianCalendar();
        nextNotifyTime.setTimeInMillis(millis);

        Date nextDate = nextNotifyTime.getTime();
        String date_text = new SimpleDateFormat("yyyy년 MM월 dd일 EE요일 a hh시 mm분 ", Locale.getDefault()).format(nextDate);
        Toast.makeText(getApplicationContext(), "[처음 실행시] 당므 알람은" + date_text + "으로 알람이 설정되었습니다.", Toast.LENGTH_SHORT).show();

        Date currentTime = nextNotifyTime.getTime();
        SimpleDateFormat HourFormat = new SimpleDateFormat("kk", Locale.getDefault());
        SimpleDateFormat MinuteFormat = new SimpleDateFormat("mm", Locale.getDefault());

        int pre_hour = Integer.parseInt(HourFormat.format(currentTime));
        int pre_minute = Integer.parseInt(MinuteFormat.format(currentTime));

        alarmTimePicker.setHour(pre_hour);
        alarmTimePicker.setMinute(pre_minute);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            alarmTimePicker.setHour(pre_hour);
//            alarmTimePicker.setMinute(pre_minute);
//        } else {
//            alarmTimePicker.setCurrentHour(pre_hour);
//            alarmTimePicker.setCurrentMinute(pre_minute);
//        }

        applyButton = findViewById(R.id.ApplyButton);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour, hour_24, minute;
                String am_pm;

                hour_24 = alarmTimePicker.getHour();
                minute = alarmTimePicker.getMinute();
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    hour_24 = alarmTimePicker.getHour();
//                    minute = alarmTimePicker.getMinute();
//                } else {
//                    hour_24 = alarmTimePicker.getCurrentHour();
//                    minute = alarmTimePicker.getCurrentMinute();
//                }

                if (hour_24 > 12) {
                    am_pm = "PM";
                    hour = hour_24 - 12;
                } else {
                    am_pm = "AM";
                    hour = hour_24;
                }

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, hour_24);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);


            }
        });

        cancelButton = findViewById(R.id.CancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
