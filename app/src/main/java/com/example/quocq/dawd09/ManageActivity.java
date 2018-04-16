package com.example.quocq.dawd09;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ManageActivity extends AppCompatActivity {

    EditText txtId;
    EditText txtNgaySinh;
    EditText txtTen;
    EditText txtEmail;

    EmployeeHelper db = new EmployeeHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        init();

    }

    void init()
    {
        txtEmail = findViewById(R.id.txtEmail);
        txtId = findViewById(R.id.txtQuery);
        txtTen = findViewById(R.id.txtTen);
        txtNgaySinh= findViewById(R.id.txtNgaySinh);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dropdown, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.btnInsert:
                startActivity(new Intent(this, ManageActivity.class));
                return true;

            case R.id.btnHome:
                startActivity(new Intent(this, ListActivity.class));
                return true;

            case R.id.btnFind:
                startActivity(new Intent(this, FindActivity.class));
                return true;

            case R.id.btnExit:
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                finishAffinity();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void doThem(View view)
    {
        if(TextUtils.isEmpty(txtEmail.getText().toString())
                ||TextUtils.isEmpty(txtTen.getText().toString())
                ||TextUtils.isEmpty(txtEmail.getText().toString())
                ||TextUtils.isEmpty(txtNgaySinh.getText().toString())
                )
        {
            Toast.makeText(this, "Null field(s) error !", Toast.LENGTH_LONG).show();
        }
        else
        {
            Employee e = new Employee();
            e.setId(txtId.getText().toString());
            e.setTen(txtTen.getText().toString());
            e.setNgaysinh(txtNgaySinh.getText().toString());
            e.setEmail(txtEmail.getText().toString());

            db.addEmployee(e);

        }

        Toast.makeText(this, db.findAll().size()+ "", Toast.LENGTH_LONG).show();
    }


    public void toList(View view)
    {
        startActivity(new Intent(this, ListActivity.class));
    }

}
