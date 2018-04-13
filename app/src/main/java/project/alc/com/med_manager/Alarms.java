package project.alc.com.med_manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by 4ran6 on 11/04/2018.
 */

public class Alarms extends BroadcastReceiver {
    String name;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        name = bundle.getString("name");

        //start activity
        Intent i = new Intent();
        i.putExtra("name", name);
        i.setClassName(context.getApplicationContext(), "com.test.MainActivity");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
