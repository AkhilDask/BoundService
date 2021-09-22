package com.example.boundservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Myservice myservice;
    boolean isBind = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textview);
        Intent intent = new Intent(this,Myservice.class);
        bindService(intent,Mconnection, Context.BIND_AUTO_CREATE); //this will bind the service to this activity
    }



    public void getMessageOne(View view){
        String message;
        message=myservice.getFirstMessage();
        textView.setText(message);

    }



    public void getMessageTwo(View view){
        String message;
        message= myservice.getSecondMessage();
        textView.setText(message);

    }
    //whenever a component is binded to the service soon after binding is disconnected android system calls onServiceConnected method
    // and the client will get ibinder object from this method
    private ServiceConnection Mconnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            Myservice.LocalService localService = (Myservice.LocalService)iBinder; //we have objectof local service with with we get object of getservice
            myservice = localService.getService();
            isBind= true; //the service is binded to the activity

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBind= false;

        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        if (isBind)
        {
            unbindService(Mconnection);
            isBind=false;
        }
    }
}