package by.grodno.toni7777.socialnetwork.push;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.gson.Gson;

import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.network.model.PushMessageDTO;
import by.grodno.toni7777.socialnetwork.network.model.PushPostDTO;
import by.grodno.toni7777.socialnetwork.ui.wall.WallActivity;
import by.grodno.toni7777.socialnetwork.util.SettingsUtil;

import static by.grodno.toni7777.socialnetwork.util.Constants.NOTIFICATION_TIME_LIGHT_OFF;
import static by.grodno.toni7777.socialnetwork.util.Constants.NOTIFICATION_TIME_LIGHT_ON;
import static by.grodno.toni7777.socialnetwork.util.Constants.NOTIFICATION_VIBRATE;

public final class NotifManager {

    public static Notification getPostNotification(Context context, SettingsUtil settings, com.google.firebase.messaging.RemoteMessage.Notification firebaseNotif) {
        Intent intent = new Intent(context, WallActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.avatar_default)
                .setContentTitle(firebaseNotif.getTitle())
                .setContentText(new Gson().fromJson(firebaseNotif.getBody(), PushPostDTO.class).getText())
                .setAutoCancel(true)
                .setLights(Color.YELLOW, NOTIFICATION_TIME_LIGHT_ON, NOTIFICATION_TIME_LIGHT_OFF)
                .setContentIntent(pendingIntent);

        if (settings.getNotificationSound()) {
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            notificationBuilder.setSound(defaultSoundUri);
        }
        if (settings.getNotificationVibrate()) {
            notificationBuilder.setVibrate(NOTIFICATION_VIBRATE);
        }

        return notificationBuilder.build();
    }

    public static Notification getMessageNotification(Context context, SettingsUtil settings, com.google.firebase.messaging.RemoteMessage.Notification firebaseNotif) {
        // TODO: 8/30/16 open chat
//        Intent intent = new Intent(context, WallActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 /* Request code */, intent,
//                PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.avatar_default)
                .setContentTitle(firebaseNotif.getTitle())
                .setContentText(new Gson().fromJson(firebaseNotif.getBody(), PushMessageDTO.class).getMessage())
                .setAutoCancel(true)
                .setLights(Color.YELLOW, NOTIFICATION_TIME_LIGHT_ON, NOTIFICATION_TIME_LIGHT_OFF);
//                .setContentIntent(pendingIntent);

        if (settings.getNotificationSound()) {
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            notificationBuilder.setSound(defaultSoundUri);
        }
        if (settings.getNotificationVibrate()) {
            notificationBuilder.setVibrate(NOTIFICATION_VIBRATE);
        }
        return notificationBuilder.build();
    }

    private NotifManager() {
    }
}
