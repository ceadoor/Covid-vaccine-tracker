package com.cea.covidshield;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.widget.Toast;



public class BackGround extends BroadcastReceiver {

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        // For our recurring task, we'll just display a message
        Toast.makeText(arg0, "Background process is triggered every minute!!! ", Toast.LENGTH_LONG).show();

    }


}
