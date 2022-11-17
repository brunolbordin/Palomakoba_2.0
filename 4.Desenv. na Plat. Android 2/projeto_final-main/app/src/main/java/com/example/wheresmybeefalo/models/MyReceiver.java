package com.example.wheresmybeefalo.models;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    private TextView txtBattery;
    @Override
    public void onReceive(Context context, Intent intent) {
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        Log.i("Bateria", "Nivel:" + level);
        if (level < 50){
            Toast.makeText(context, "Sua bateria está abaixo de 50% da carga", Toast.LENGTH_SHORT).show();
        }
    }
}