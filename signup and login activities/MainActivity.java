package com.example.dilate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyMessage";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreate");

        /**
         * Calling next activity with the click of login text view
         */
        TextView tvLogin = (TextView)findViewById(R.id.tv_main_login);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.tv_main_login)
                //Intent intentLogin = new Intent(this, Login.class);
                {
                    /**
                     * in order to call new intent or activity we need to pass the first parameter as application context
                     */
                    Intent intentLogin = new Intent(v.getContext(),Login.class);
                    //intentLogin = new Intent(getApplicationContext(), Login.class);
                    startActivity(intentLogin);
                }
            }
        });

        /**
         * Calling new activity on pressing button signup
         */
        Button signup = (Button)findViewById(R.id.btn_main_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_main_signup)
                {
                    Intent loadsignup = new Intent(v.getContext(),Signup.class);
                    startActivity(loadsignup);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"onSaveInstanceState");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"onRestart");
    }
}
