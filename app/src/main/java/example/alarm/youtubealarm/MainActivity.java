package example.alarm.youtubealarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    AlarmManager alarmManager;
    TimePicker alarmTimePicker;
    TextView updateText;
    Context context;
    PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmTimePicker = (TimePicker) findViewById(R.id.timePicker);

        updateText = (TextView) findViewById(R.id.update_text);

        final Calendar  calendar  = Calendar.getInstance();

        Button startAlarm = (Button) findViewById(R.id.start_alarm);
        final Intent myIntent = new Intent(this.context, AlarmReceiver.class);
        startAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY,alarmTimePicker.getHour());
                calendar.set(Calendar.MINUTE, alarmTimePicker.getMinute());

                int hour = alarmTimePicker.getHour();
                int minute = alarmTimePicker.getMinute();

                String stringHour = String.valueOf(hour);
                String stringMinute = String.valueOf(minute);
                setAlarmText("Alarm set to" + stringHour + ":" + stringMinute);

                myIntent.putExtra("extra","on");
                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pendingIntent);
            }
        });



        Button endAlarm = (Button) findViewById(R.id.end_alarm);
        endAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarmText("Alarm off");

                //alarmManager.cancel(pendingIntent);

                myIntent.putExtra("extra","off");
                sendBroadcast(myIntent);
            }
        });

    }

    private void setAlarmText(String s) {
        updateText.setText(s);
    }
}
