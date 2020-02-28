package com.example.dilate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    //https://www.youtube.com/watch?v=EM2x33g4syY
    EditText etEmailsignup,etPasswordsignup,etRepPasswordsignup;
    Button btnSignup;
    UserModel signUpUser;
    private FirebaseAuth mAuth;

    //Creating database reference
    DatabaseReference databaseNewUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Get the reference
        databaseNewUser = FirebaseDatabase.getInstance().getReference("NewUser");

        etEmailsignup = findViewById(R.id.et_signup_email);
        etPasswordsignup = findViewById(R.id.et_signup_password);
        etRepPasswordsignup = findViewById(R.id.et_signup_reppassword);
        btnSignup = findViewById(R.id.btn_signup_signup);


// ...
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //addNewUser();
                addUserwithAuth();
            }
        });
    }

    private void addUserwithAuth() {

        final String email =  etEmailsignup.getText().toString().trim();
        final String password = etPasswordsignup.getText().toString().trim();
        String repPass = etRepPasswordsignup.getText().toString().trim();



        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Hey", "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();

                    String userId = databaseNewUser.push().getKey();
                    signUpUser = new UserModel(email,password,userId);

                    databaseNewUser.child(userId).setValue(signUpUser);
                    //updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Hello", "createUserWithEmail:failure", task.getException());
                    Toast.makeText(Signup.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                   // updateUI(null);
                }
            }


    });
    }

    private void addNewUser() {
        String email =  etEmailsignup.getText().toString().trim();
        String password = etPasswordsignup.getText().toString().trim();
        String repPass = etRepPasswordsignup.getText().toString().trim();

        if(!(email.isEmpty() || password.isEmpty() || repPass.isEmpty()))
        {
             String userId = databaseNewUser.push().getKey();
             signUpUser = new UserModel(email,password,userId);
             databaseNewUser.child(userId).setValue(signUpUser);

             Toast.makeText(this,"User Registered",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"Empty Values",Toast.LENGTH_LONG).show();
        }
    }
}
