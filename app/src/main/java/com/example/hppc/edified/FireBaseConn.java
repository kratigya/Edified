package com.example.hppc.edified;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Prafful on 01-02-2017.
 */

public interface FireBaseConn {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
}
