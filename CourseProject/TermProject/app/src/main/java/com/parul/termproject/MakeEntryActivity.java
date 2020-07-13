package com.parul.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MakeEntryActivity extends AppCompatActivity {

    private Button b1;
    DatabaseReference mentry;
    private EditText t1;
    private Button eroll;
    private String rollno;
    public static final String EXTRA_MESSAGE = "com.parul.termproject.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_entry);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mentry = database.getReference("records");
        b1 = (Button) findViewById(R.id.go);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Make_Entry();
            }
        });

        t1 = (EditText) findViewById(R.id.roll);
        eroll = (Button) findViewById(R.id.enter);
            eroll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rollno = t1.getText().toString();
                    if (rollno.isEmpty()) {
                        Toast.makeText(MakeEntryActivity.this, "Enter Roll NO", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent i = new Intent(MakeEntryActivity.this, DisplayInfo.class);
                        i.putExtra(EXTRA_MESSAGE, rollno);
                        startActivity(i);
                    }
                }
            });
    }

    public void Make_Entry() {
        String name = "Aakanksha";
        String RollNo = "17Mi549";
        String Category = "Integrated M.Tech";
        String deptt = "CSE";

        Entry entry = new Entry (name, RollNo, Category, deptt);
        mentry.child(RollNo).setValue(entry);
    }
}
