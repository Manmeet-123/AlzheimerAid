package com.decode.alzaid;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

public class BootReceiver extends BroadcastReceiver {
    public BootReceiver() {
    }

    public static boolean RECEIVER_ACTIVE = false;

    public static final long REPEAT_TIME = 1000 * 60 * 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Service","Boot Received");
        if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
            Intent serviceIntent = new Intent(context, TrackerUpdate.class);
            context.startService(serviceIntent);

            AlarmManager service = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(System.currentTimeMillis());
            cal.add(Calendar.SECOND,3);
            //endingIntent pi = PendingIntent.getBroadcast(context.getApplicationContext(),0,i,0);
            PendingIntent pi2 = PendingIntent.getService(context,6969,serviceIntent,PendingIntent.FLAG_CANCEL_CURRENT);
            service.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), REPEAT_TIME , pi2);
            //service.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),pi2);
        }

    }
}
