package com.example.todolist;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class TaskReminderReceiver extends BroadcastReceiver {

    private static final String ACTION_OK = "com.example.todolist.ActionActivity";

    @Override
    public void onReceive(Context context, Intent intent) {
        String taskName = intent.getStringExtra("TASK_NAME");

        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent okIntent = new Intent(context, ActionActivity.class);
        PendingIntent okPendingIntent = PendingIntent.getBroadcast(context, 0, okIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NotificationHelper.CHANNEL_ID)
                .setSmallIcon(R.drawable.icon_2)
                .setContentTitle("Todo List")
                .setContentText("You have a task: " + taskName)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.round_verified_24, "OK", okPendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager != null){
            notificationManager.notify(1, builder.build());
        }
    }
}
