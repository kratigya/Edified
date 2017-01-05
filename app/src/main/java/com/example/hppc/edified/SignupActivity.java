package com.example.hppc.edified;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {

    private EditText fname, lname, email, passwd, confirm_passwd;
    private Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fname = (EditText) findViewById(R.id.first_name);
        lname = (EditText) findViewById(R.id.last_name);
        email = (EditText) findViewById(R.id.email_address);
        passwd = (EditText) findViewById(R.id.passwd);
        confirm_passwd = (EditText) findViewById(R.id.confirm_password);

        create = (Button) findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User usr = new User();
                usr.setFirstName(fname.getText().toString());
                usr.setLastName(lname.getText().toString());
                usr.setEmailAddress(email.getText().toString());
                usr.setPassword(passwd.getText().toString());

                boolean flag = validate(usr);

                if (flag) {
                    //to write code to connect using api and create user

                }

            }
        });

    }

    private boolean validate(User usr) {
        //to write validation code

        return true;
    }

}
