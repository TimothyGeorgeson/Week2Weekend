package com.example.consultants.week2weekend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MusicActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        super.onCreateDrawer();
    }
}
