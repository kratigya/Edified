package com.example.hppc.edified;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseUser;

public class ChooseRole extends AppCompatActivity implements FireBaseConn {

    private ImageButton fac, stu;
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fac = (ImageButton) findViewById(R.id.faculty);
        stu = (ImageButton) findViewById(R.id.student);

        fac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                role = "Faculty";
                addRole(role);
                Intent intent = new Intent(ChooseRole.this, MainActivity.class);
                startActivity(intent);
            }
        });

        stu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                role = "Student";
                addRole(role);
                Intent intent = new Intent(ChooseRole.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void addRole(String role) {
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child("users").child(user.getUid()).child("role").setValue(role);
    }

}
