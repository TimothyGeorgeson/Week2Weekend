package com.example.consultants.week2weekend.job;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class MyRunnable implements Runnable {

    int fibInput;
    Handler handler;

    public MyRunnable(int fibInput, Handler handler) {
        this.fibInput = fibInput;
        this.handler = handler;
    }

    @Override
    public void run() {

        Bundle bundle = new Bundle();
        Message message = new Message();
        bundle.putString("Task starting", "someValue");
        message.setData(bundle);
        handler.sendMessage(message);

        //start the task
        try {
            FibTask.start(fibInput);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //HandlerUtils.sendMessage(handler, "Task completed");
    }
}
