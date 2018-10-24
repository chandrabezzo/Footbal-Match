package com.bezzo.core.util;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.support.v4.app.*;

import com.bezzo.core.*;
import com.bezzo.core.util.constanta.*;

/**
 * Created by bezzo on 09/01/18.
 */

public class NotificationUtils {

    private static NotificationChannel configureChannel(String channelId, String channelName, String channelDescription){
        NotificationChannel channel = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            channel = new NotificationChannel(channelId, channelName, importance);

            if (channelDescription != null){
                channel.setDescription("Reminders");
            }
        }

        return channel;
    }

    public static void createNotification(int notifId, String title, String messageBody, Context context,
                                          Class<?> notificationTarget, int resourceId){
        Bundle data = new Bundle();
        data.putString(AppConstans.INSTANCE.getFCM_MESSAGE(), messageBody);

        Intent intent = new Intent(context, notificationTarget);
        intent.putExtras(data);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        int requestID = (int) System.currentTimeMillis();
        int flags = PendingIntent.FLAG_CANCEL_CURRENT;
        PendingIntent pIntent = PendingIntent.getActivity(context, requestID, intent, flags);

        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), resourceId);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, BuildConfig.APPLICATION_ID)
                .setLargeIcon(largeIcon)
                .setSmallIcon(resourceId)
                .setContentTitle(title)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .setTicker(messageBody)
                .setContentText(messageBody)
                .setDefaults(Notification.DEFAULT_ALL);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mBuilder.setPriority(NotificationManager.IMPORTANCE_HIGH);
        }
        else {
            mBuilder.setPriority(Notification.PRIORITY_HIGH);
        }

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mNotificationManager.createNotificationChannel(NotificationUtils.configureChannel(BuildConfig.APPLICATION_ID,
                    context.getString(R.string.app_name), null));
        }

        mNotificationManager.notify(BuildConfig.APPLICATION_ID, notifId, mBuilder.build());
    }
}