package com.example.notifapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // récupération du notification manager :
        NotificationManager manager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // cas Android 8 et plus :
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && manager != null)
        {
            // description du groupe :
            NotificationChannel channel = new NotificationChannel("groupe1",
                    "Nom du groupe", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Description du groupe");
            channel.enableLights(true);
            // comportement des notifications du groupe :
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 200, 300});
            manager.createNotificationChannel(channel);
        }
    }

    public void clickNotif(View view) {
        // construction notification :
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "groupe1")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Ma notification")
                .setAutoCancel(true)
                .setContentText("Bienvenue !");

        // action simple :
        Intent intent = new Intent(this, NotifActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotifActivity.class);
        stackBuilder.addNextIntent(intent);

        //Sans Back stack
        //PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //Avec Back stack
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);

        // branchement entre le xml et la notification :
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.example_notfi);
        remoteViews.setOnClickPendingIntent(R.id.btnnotif, pendingIntent);
        builder.setCustomContentView(remoteViews);

        // affichage notification (si ID existant, remplace la précédente) :
        NotificationManager manager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.notify(1234, builder.build());
        }
    }
}
