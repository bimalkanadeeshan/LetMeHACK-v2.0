package com.letmehack.administrator.letmehack1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SponsoreActivity extends AppCompatActivity {
    private android.support.v7.widget.Toolbar mToolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsore);

        //Toolbar
        mToolBar = findViewById(R.id.register_toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("Sponsors");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
