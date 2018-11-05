package com.example.consultants.week2weekend.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.consultants.week2weekend.job.MyHandlerThread;
import com.example.consultants.week2weekend.R;

public class FibonacciActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar myToolbar;
    EditText etFib;
    TextView tvResult;
    private MyHandlerThread handlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci);
        etFib = findViewById(R.id.etFib);
        tvResult = findViewById(R.id.tvResult);

        drawerLayout = findViewById(R.id.navDrawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);
        drawerToggle.syncState();
    }

    public void calcFibonacci(View view) {
        String input = etFib.getText().toString();
        boolean containsNonInt = false;
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                containsNonInt = true;
                break;
            }
        }
        if (containsNonInt) {
            Toast.makeText(this, "Enter integers only", Toast.LENGTH_SHORT).show();
        } else {
            //setting up HandlerThread
            handlerThread = new MyHandlerThread("MyHandlerThread");
            //runnable that contains fibonacci task
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    int output = fibonacci(Integer.parseInt(etFib.getText().toString()));
                    tvResult.setText(Integer.toString(output));

                    //saving highest fibonacci value into shared prefs
                    SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("Prefs", Context.MODE_PRIVATE);

                    int highestFib = sharedPref.getInt("FibMax", 0);
                    if (output > highestFib) {
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putInt("FibMax", output);
                        editor.commit();
                    }
                }
            };

            handlerThread.start();
            handlerThread.prepareHandler();
            handlerThread.postTask(task);
            handlerThread.postTask(task);

        }
    }

    @Override
    protected void onDestroy() {
        handlerThread.quit();
        super.onDestroy();
    }

    static int fibonacci(int n) {
        if (n <= 1)
            return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.home:
                this.finish();
                return true;
            case R.id.itemBack:
                finish();
                return true;
            case R.id.itemReport:
                startActivity(new Intent(this, ReportActivity.class));
                break;
            case R.id.itemFact:
                startActivity(new Intent(this, FactorialActivity.class));
                break;
            case R.id.itemFib:
                startActivity(new Intent(this, FibonacciActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Intent intent;
        switch (menuItem.getItemId()) {
            case R.id.home:
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.quiz:
                Toast.makeText(this, "Quiz Not Yet Implemented", Toast.LENGTH_SHORT).show();
                break;
            case R.id.music:
                intent = new Intent(getApplicationContext(), MusicActivity.class);
                startActivity(intent);
                break;
            case R.id.exit:
                this.finish();
                break;
        }

        return false;
    }
}
