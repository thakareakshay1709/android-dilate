package com.example.dilate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    EditText et_login_email,et_login_password;
    Button btn_login_login;


    //Creating database reference
    DatabaseReference databaseGetUser;
    UserModel retrieveUser;
    //List<String> emailList;

    private FirebaseAuth mAuth;


    //https://www.youtube.com/watch?v=V0ZrnL-i77Q&t=175s
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Get the reference
        databaseGetUser = FirebaseDatabase.getInstance().getReference("NewUser");
        //emailList = new ArrayList<>();


        //// ...
        //// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        et_login_email = (EditText)findViewById(R.id.et_login_email);
        et_login_password = (EditText)findViewById(R.id.et_login_password);
        btn_login_login = (Button)findViewById(R.id.btn_login_login);

        btn_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loginUser(v);
                //dummyLogin(v);
                loginWithUserAuth(v);
            }


        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    private void loginWithUserAuth(final View v) {

        String userName = et_login_email.getText().toString();
        String passWord = et_login_password.getText().toString();

        mAuth.signInWithEmailAndPassword(userName,passWord).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Signin", "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Intent userProfile = new Intent(v.getContext(), UserProfile.class);
                    startActivity(userProfile);
                    //updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Signin", "signInWithEmail:failure", task.getException());
                    Toast.makeText(Login.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    //updateUI(null);
                }
            }
        });
    }

    private void dummyLogin(View v) {

        String userName = et_login_email.getText().toString();
        String passWord = et_login_password.getText().toString();

        if(userName.equals("akshay") && passWord.equals("1234"))
        {
            Toast.makeText(Login.this,"Success",Toast.LENGTH_LONG).show();
            Intent userProfile = new Intent(Login.this, UserProfile.class);
            startActivity(userProfile);
        }
        else
        {
            Toast.makeText(Login.this,"Incorrect Details",Toast.LENGTH_LONG).show();
        }
    }

    private void loginUser(View v) {

        String emailLogin = et_login_email.getText().toString().trim();
        final String passLogin = et_login_password.getText().toString().trim();

        //final String key = databaseGetUser.push().getKey().toString();
        if(databaseGetUser.child(emailLogin)!= null)
        {
            Log.i("LoginActivity",databaseGetUser.child(databaseGetUser.getKey()).child(emailLogin).toString());
            Log.i("LoginActivity2",databaseGetUser.getKey());
            databaseGetUser.child(emailLogin).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //retrieveUser = new UserModel();
                    //retrieveUser = dataSnapshot.getValue(UserModel.class);
                    String pass = dataSnapshot.child("password").getValue().toString();

                    //System.out.println("Email "+retrieveUser.getEmail()+", pass"+retrieveUser.getPassword());
                    //Log.i("LoginActivityretrieve",retrieveUser.getEmail().toString());
                   if(passLogin.equals(pass))
                    {
                        Toast.makeText(Login.this,"Success",Toast.LENGTH_LONG).show();
                        Intent userProfile = new Intent(Login.this, UserProfile.class);
                        startActivity(userProfile);
                    }
                    else
                    {
                        Toast.makeText(Login.this,"No",Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else
        {
            Toast.makeText(Login.this,"Record does not exist",Toast.LENGTH_LONG).show();
        }






    }
}
