package com.example.test_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by 杨天宇 on 2016/7/16.
 */
public class OutCallReceiver extends BroadcastReceiver {
    private static final String TAG = "OutCallReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: 已经被调用？？？？？");
        String outcallnumber = getResultData();
        SharedPreferences sp = context.getSharedPreferences("config",Context.MODE_PRIVATE);
        String ipNumber = sp.getString("ipNumber","");
        setResultData(ipNumber+outcallnumber);
    }
}
