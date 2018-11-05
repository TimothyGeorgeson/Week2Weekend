package com.example.consultants.week2weekend.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.consultants.week2weekend.R;

public class ReportActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar myToolbar;
    private TextView tvFactResult;
    private TextView tvFibResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        drawerLayout = findViewById(R.id.navDrawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);
        drawerToggle.syncState();

        tvFactResult = findViewById(R.id.tvFactResult);
        tvFibResult = findViewById(R.id.tvFibResult);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("Prefs", Context.MODE_PRIVATE);
        int highestFact = sharedPref.getInt("FactMax", 0);
        int highestFib = sharedPref.getInt("FibMax", 0);

        tvFactResult.setText(Integer.toString(highestFact));
        tvFibResult.setText(Integer.toString(highestFib));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
        {
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
        switch (menuItem.getItemId())
        {
            case R.id.home:
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.quiz:
                Toast.makeText(this, "This is quiz", Toast.LENGTH_SHORT).show();
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
