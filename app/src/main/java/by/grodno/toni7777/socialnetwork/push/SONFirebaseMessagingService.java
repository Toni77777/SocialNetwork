package by.grodno.toni7777.socialnetwork.push;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import by.grodno.toni7777.socialnetwork.R;
import by.grodno.toni7777.socialnetwork.ui.wall.WallActivity;

import static by.grodno.toni7777.socialnetwork.util.Constants.*;

import by.grodno.toni7777.socialnetwork.util.LoginUtil;
import by.grodno.toni7777.socialnetwork.util.SettingsUtil;

public class SONFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getNotification() != null) {
            sendNotification(remoteMessage.getNotification());
        }
    }

    private void sendNotification(com.google.firebase.messaging.RemoteMessage.Notification firebaseNotif) {
        if (LoginUtil.isLogined(getBaseContext())){
            SettingsUtil settings = new SettingsUtil(getApplicationContext());
            if (settings.getShowNotification()) {
                Intent intent = new Intent(getBaseContext(), WallActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0 /* Request code */, intent,
                        PendingIntent.FLAG_ONE_SHOT);

                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getBaseContext())
                        .setSmallIcon(R.drawable.avatar_default)
                        .setContentTitle(firebaseNotif.getTitle())
                        .setContentText(firebaseNotif.getBody())
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

                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
            }
        }
    }
}
