package example.alarm.youtubealarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by KaikaiFu on 2017/3/11.
 */

//this is an test

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Receiver","we are in the receiver");

        String getYourString =  intent.getExtras().getString("extra");

        Log.e("what is the key?", getYourString);

        Intent serviceIntent = new Intent(context, RingtonePlayingService.class);
        serviceIntent.putExtra("extra", getYourString);
        context.startService(serviceIntent);
    }
}
