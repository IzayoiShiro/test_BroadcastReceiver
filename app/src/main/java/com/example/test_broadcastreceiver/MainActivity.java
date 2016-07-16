package com.example.test_broadcastreceiver;

import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_ipNumber;
    private SharedPreferences sp;
    private OutCallReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_ipNumber = (EditText) findViewById(R.id.et_ipNumber);
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        et_ipNumber.setText(sp.getString("ipNumber",""));
        receiver = new OutCallReceiver();
        String action = "android.intent.action.NEW_OUTGOING_CALL";
        IntentFilter filter = new IntentFilter(action);
        registerReceiver(receiver, filter);
    }

    public void click(View view) {
        String ipNumber = et_ipNumber.getText().toString().trim();
        Editor editor = sp.edit();
        editor.putString("ipNumber",ipNumber);
        editor.commit();
        Toast.makeText(this,"设置成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
