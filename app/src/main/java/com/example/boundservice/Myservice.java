package com.example.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class Myservice extends Service {
    private final IBinder mBinder = new LocalService();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder; //any client connected to the binder will get object of LocalService
        //by using that object the client can call getService() fom that the client will get object of Myservice
        //using which client can call any method available in this service
    }

    public class LocalService extends Binder
    {
        //ths method returns an object of service class (Myservice)
        Myservice getService(){

            return Myservice.this;
        }

    }
    //public methods
    public  String getFirstMessage(){

        return "hello friends welcome";
    }
    public  String getSecondMessage(){
        return "This is your second message";
    }
}
