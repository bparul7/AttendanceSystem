package com.parul.termproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private EditText email;
    private EditText password;
    private Button b1;
    private TextView tvin;
    private String Email, Pass;

    public void Enter () {

        mAuth.signInWithEmailAndPassword(Email, Pass)
                .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            startActivity(new Intent (SignInActivity.this, Profile.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignInActivity.this, "Log In failed.",
                                    Toast.LENGTH_SHORT).show();
                            // ...
                        }

                        // ...
                    }
                });
    }

    public void SignIn () {
        Email = email.getText().toString();
        Pass = password.getText().toString();
        // Toast.makeText(SignUpActivity.this, Email+Pass, Toast.LENGTH_SHORT).show();

        if (Email.isEmpty()) {
            email.setError("enter your email");
            email.requestFocus();
        }
        else if (Pass.isEmpty()) {
            password.setError("Enter your password");
            password.requestFocus();
        }
        else if (Email.isEmpty() && Pass.isEmpty()) {
            Toast.makeText(SignInActivity.this, "Fields Are Empty", Toast.LENGTH_SHORT).show();
        }
        else if (!(Email.isEmpty() && Pass.isEmpty())) {
            Enter();
        }
        else {
            Toast.makeText(SignInActivity.this, "Some Error Occured", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mfirebaseuser = mAuth.getCurrentUser();
                if (mfirebaseuser != null) {
                    startActivity(new Intent (SignInActivity.this, Profile.class));
                }
                else {
                    Toast.makeText(SignInActivity.this, "Please LogIn", Toast.LENGTH_SHORT).show();
                }
            }
        };

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pass);
        b1 = (Button) findViewById(R.id.signin);
        tvin = (TextView) findViewById(R.id.movesignup);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SignIn();
            }
        });

        tvin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent (SignInActivity.this, SignUpActivity.class);
                startActivity(ii);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.addAuthStateListener(mAuthStateListener);
    }
}
