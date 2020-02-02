package com.android.maplestoryassistant;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TimePicker alarmTimePicker;
    Spinner channelSpinner;

    RadioGroup mapRadioGroup;
    RadioButton otherMapRadioButton;

    LinearLayout otherMapLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmTimePicker = findViewById(R.id.AlarmTimePicker);
        alarmTimePicker.setIs24HourView(true);

        channelSpinner = findViewById(R.id.ChannelSpinner);
        ArrayAdapter channelAdapter = ArrayAdapter.createFromResource(this, R.array.channel, android.R.layout.simple_spinner_item);
        channelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        channelSpinner.setAdapter(channelAdapter);

        mapRadioGroup = findViewById(R.id.MapRadioGroup);
        otherMapRadioButton = findViewById(R.id.OtherMapRadioButton);
        otherMapLayout = findViewById(R.id.OtherMapLayout);

        mapRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.OtherMapRadioButton) {
                    Toast.makeText(MainActivity.this, "직접입력 선택", Toast.LENGTH_LONG).show();
                    otherMapLayout.setVisibility(View.VISIBLE);
                } else {
                    otherMapLayout.setVisibility(View.GONE);
                }
            }
        });
    }
}
