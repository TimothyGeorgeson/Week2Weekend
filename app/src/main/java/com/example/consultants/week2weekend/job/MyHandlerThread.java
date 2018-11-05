package com.example.consultants.week2weekend.job;

import android.os.Handler;
import android.os.HandlerThread;

public class MyHandlerThread extends HandlerThread {

    private Handler mWorkerHandler;

    public MyHandlerThread(String name) {
        super(name);
    }

    public void postTask(Runnable task){
        mWorkerHandler.post(task);
    }

    public void prepareHandler(){
        mWorkerHandler = new Handler(getLooper());
    }
}
