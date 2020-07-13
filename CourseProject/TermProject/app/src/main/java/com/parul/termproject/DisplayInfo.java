package com.parul.termproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DisplayInfo extends AppCompatActivity {

    DatabaseReference mentry;
    String rollno;
    TextView pname;
    TextView prollno;
    TextView pcategory;
    TextView pbranch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mentry = database.getReference("records");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        rollno = intent.getStringExtra(MakeEntryActivity.EXTRA_MESSAGE);
        Log.i ("Test Roll No", rollno);
        pname = (TextView) findViewById(R.id.printname);
        prollno = (TextView) findViewById(R.id.printrollno);
        pcategory = (TextView) findViewById(R.id.printcategory);
        pbranch = (TextView) findViewById(R.id.printdepartment);

        mentry.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean ok = false;
                for (DataSnapshot record : snapshot.getChildren()) {
                    Entry rec = record.getValue(Entry.class);
                    Log.i ("Roll NO;", rec.getRollNo());
                    if (rec.getRollNo().equals(rollno)) {
                        pname.setText(rec.getName());
                        prollno.setText(rec.getRollNo());
                        pcategory.setText(rec.getCategory());
                        pbranch.setText(rec.getDeptt());
                        ok = true;
                        break;
                    }
                }
                if (ok == false) {
                    Toast.makeText(DisplayInfo.this, "Roll No Not Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DisplayInfo.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
