package com.dpaulos6.localelens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dpaulos6.localelens.MainActivity2;
import com.dpaulos6.localelens.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent in = new Intent(getApplicationContext(), MainActivity2.class);
            startActivity(in);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
    }

    public void registerAccount(View view) {

        String email, password;

        EditText edemail = findViewById(R.id.edemail);
        EditText edpassword = findViewById(R.id.edpassword);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        email = edemail.getText().toString();
        password = edpassword.getText().toString();
        progressBar.setVisibility(View.VISIBLE);


        if (TextUtils.isEmpty(email)){
            Toast.makeText(Register.this, "Email input is empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(Register.this, "Password input is empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Account created.",
                                    Toast.LENGTH_SHORT).show();
                            Intent in = new Intent(getApplicationContext(), Login.class);
                            startActivity(in);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Register.this, "Account creation failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void loginAccount(View view) {
        Intent in = new Intent(this, Login.class);
        startActivity(in);
        finish();
    }
}