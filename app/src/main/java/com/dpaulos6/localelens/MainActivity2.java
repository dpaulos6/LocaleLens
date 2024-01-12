package com.dpaulos6.localelens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dpaulos6.localelens.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // txt com o utilizador  e o button para fazer logout

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user == null){
            Intent in = new Intent(this, com.dpaulos6.localelens.Login.class);
            startActivity(in);
            finish();
        } else {
            TextView txtUser = findViewById(R.id.txtUser);
            txtUser.setText(user.getEmail());
        }

        // code added 19/11/2023
        try {
            Intent in = new Intent();
            in.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
            startActivity(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logoutAccount(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent in = new Intent(this, com.dpaulos6.localelens.Login.class);
        startActivity(in);
        finish();
    }
}