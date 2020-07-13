package com.parul.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MakeEntryActivity extends AppCompatActivity {

    private Button b1;
    DatabaseReference mentry;

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
    }

    public void Make_Entry() {
        String name = "Aakanksha";
        String RollNo = "17MI549";
        String Category = "Integrated M.Tech";
        String deptt = "CSE";

        Entry entry = new Entry (name, RollNo, Category, deptt);
        mentry.child(RollNo).setValue(entry);
    }
}
