package com.parul.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CreateAccount extends AppCompatActivity {

    private Button sin;
    private Button sup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        sin = (Button) findViewById(R.id.sgnin);
        sup = (Button) findViewById(R.id.sgnup);
        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (CreateAccount.this, SignInActivity.class);
                startActivity(i);
            }
        });

        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(CreateAccount.this, SignUpActivity.class);
                startActivity(i1);
            }
        });
    }
}
