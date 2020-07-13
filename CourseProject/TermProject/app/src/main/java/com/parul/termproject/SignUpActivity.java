package com.parul.termproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;
    private Button b1;
    private TextView tvin;
    private String Email, Pass;

    public void Enter () {
        mAuth.createUserWithEmailAndPassword(Email, Pass).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    startActivity(new Intent (SignUpActivity.this, Profile.class));
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(SignUpActivity.this, "Account Not Created.", Toast.LENGTH_SHORT).show();
                }

                // ...
            }
        });
        mAuth.createUserWithEmailAndPassword(Email, Pass)
                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Signup", "createUserWithEmail:success");
                            startActivity(new Intent (SignUpActivity.this, Profile.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("error", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Account Not Created",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void SignUp () {
        Email = email.getText().toString();
        Pass = password.getText().toString();

        if (Email.isEmpty()) {
            email.setError("enter your email");
            email.requestFocus();
        }
        else if (Pass.isEmpty()) {
            password.setError("Enter your password");
            password.requestFocus();
        }
        else if (Email.isEmpty() && Pass.isEmpty()) {
            Toast.makeText(SignUpActivity.this, "Fields Are Empty", Toast.LENGTH_SHORT).show();
        }
        else if (!(Email.isEmpty() && Pass.isEmpty())) {
           // Toast.makeText(SignUpActivity.this, "Enter", Toast.LENGTH_SHORT).show();
            Enter();
        }
        else {
            Toast.makeText(SignUpActivity.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = (EditText) findViewById(R.id.lemail);
        password = (EditText) findViewById(R.id.lpass);
        b1 = (Button) findViewById(R.id.signup);
        tvin = (TextView) findViewById(R.id.movesignin);
        mAuth = FirebaseAuth.getInstance();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp();
            }
        });

        tvin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent (SignUpActivity.this, SignInActivity.class);
                startActivity(ii);
            }
        });
    }
}
