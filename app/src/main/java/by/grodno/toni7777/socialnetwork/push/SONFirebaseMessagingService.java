package by.grodno.toni7777.socialnetwork.push;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import by.grodno.toni7777.socialnetwork.network.model.PushMessageDTO;
import by.grodno.toni7777.socialnetwork.network.model.PushPostDTO;

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
        if (LoginUtil.isLogined(getBaseContext())) {
            SettingsUtil settings = new SettingsUtil(getApplicationContext());
            if (settings.getShowNotification()) {
                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                String body = firebaseNotif.getBody();
                Notification notification = null;
                if (body.contains(PushPostDTO.message)) {
                    notification = NotifManager.getPostNotification(getBaseContext(), settings, firebaseNotif);
                } else if (body.contains(PushMessageDTO.message)) {
                    notification = NotifManager.getMessageNotification(getBaseContext(), settings, firebaseNotif);
                }

                if (notification != null) {
                    notificationManager.notify(0 /* ID of notification */, notification);
                }
            }
        }
    }
}
