package com.example.quocq.dawd09;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtUsername;
    EditText txtPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    public void init()
    {
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtUsername = (EditText) findViewById(R.id.txtUsername);

    }

    public void setNull()
    {

    }

    public void doLogin(View view)
    {
        Log.i("aaaaaaaaa0: ", txtPassword.getText().toString());
        if(txtUsername.getText().toString().equals("fpt") &&
           txtPassword.getText().toString().equals("123")) {

            startActivity(new Intent(this, ManageActivity.class));
        }
        else
            Toast.makeText(this, "Wrong username or password !",
                    Toast.LENGTH_LONG).show();
    }


}
