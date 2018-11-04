package com.example.consultants.week2weekend;

import android.content.Intent;
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

public class FactorialActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar myToolbar;
    EditText etFact;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factorial);
        etFact = findViewById(R.id.etFact);
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

    public void calcFactorial(View view) {
        String input = etFact.getText().toString();
        boolean containsNonInt = false;
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i)))
            {
                containsNonInt = true;
                break;
            }
        }
        if (containsNonInt)
        {
            Toast.makeText(this, "Enter integers only", Toast.LENGTH_SHORT).show();
        }
        else
        {
            int output = factorial(Integer.parseInt(input));
            tvResult.setText(Integer.toString(output));
        }

    }

    static int factorial(int n)
    {
        if (n == 0)
            return 1;

        return n*factorial(n-1);
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
            case R.id.itemSkip:
                Toast.makeText(this, "Skipped", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itemReport:
                Toast.makeText(this, "Report", Toast.LENGTH_SHORT).show();
                break;
            case R.id.itemFact:
                startActivity(new Intent(this, FactorialActivity.class));
                break;
            case R.id.itemFib:

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
                Toast.makeText(this, "This is settings", Toast.LENGTH_SHORT).show();
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
