package com.parul.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.parul.authentication.MESSAGE";
    public Button acc;
    public Button inf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acc = (Button) findViewById(R.id.account);
        inf = (Button) findViewById(R.id.info);

        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateAccount.class);
                intent.putExtra(EXTRA_MESSAGE , "create your account");
                startActivity(intent);
            }
        });

        inf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GetStudents.class);
                intent.putExtra(EXTRA_MESSAGE , "Search students who are out of campus");
                startActivity(intent);
            }
        });
    }
}
