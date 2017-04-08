package com.example.hppc.edified;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity implements FireBaseConn {

    private static final String TAG = "EmailPassword";
    private EditText fname, lname, email, passwd, confirm_passwd;
    private Button create;
    private RadioGroup role;
    private RadioButton radioButton;
    private String roleName;

    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fname = (EditText) findViewById(R.id.first_name);
        lname = (EditText) findViewById(R.id.last_name);
        email = (EditText) findViewById(R.id.email_address);
        passwd = (EditText) findViewById(R.id.password);
        confirm_passwd = (EditText) findViewById(R.id.confirm_password);

        create = (Button) findViewById(R.id.create);

        role = (RadioGroup) findViewById(R.id.role);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = role.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                roleName = radioButton.getText().toString();
                createAccount(email.getText().toString(), passwd.getText().toString());
            }
        });
    }

    void createAccount(final String email, String password) {
        Log.d(TAG, "createAccount:" + email);
//        showProgressDialog();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful())
                            Toast.makeText(SignupActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        else {
                            User newu = new User();
                            newu.setEmailAddress(email);
                            newu.setFirstName(fname.getText().toString());
                            newu.setLastName(lname.getText().toString());
                            newu.setRole(roleName);

                            FirebaseUser user = mAuth.getCurrentUser();
                            mDatabase.child("users").child(user.getUid()).setValue(newu);

                            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });
        // [END create_user_with_email]
    }
}
