package com.example.quocq.dawd09;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText txtId;
    EditText txtTen;
    EditText txtNgaySinh;
    EditText txtEmail;

    String id, ten, ngaysinh, email;

    Employee e = new Employee();

    EmployeeHelper db = new EmployeeHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        init();


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
                finishAffinity();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void init() {
        txtId = (EditText) findViewById(R.id.txtQuery);
        txtTen = (EditText) findViewById(R.id.txtTen);
        txtNgaySinh = (EditText) findViewById(R.id.txtNgaySinh);
        txtEmail = (EditText) findViewById(R.id.txtEmail);

        txtId.setEnabled(false);

        Bundle bundle = getIntent().getExtras();

        id = bundle.getString("id");
        ten = bundle.getString("ten");
        ngaysinh = bundle.getString("ngaySinh");
        email = bundle.getString("email");

        txtId.setText(id);
        txtTen.setText(ten);
        txtNgaySinh.setText(ngaysinh);
        txtEmail.setText(email);
//
//        for (Employee e : db.findAll())
//        {
//            if(e.getId().equals(id))
//            {
//                this.e = e;
//            }
//        }

    }

    public void doEdit(View view)
    {
        this.e.setId(id);
        this.e.setNgaysinh(txtNgaySinh.getText().toString());
        this.e.setTen(txtTen.getText().toString());
        this.e.setEmail(txtEmail.getText().toString());

        db.updateEmployee(e);

        startActivity(new Intent(this, ListActivity.class));

        Toast.makeText(this, "Edit " + e.getId() + " completed", Toast.LENGTH_SHORT).show();
    }

    public void doDelete(View view)
    {
        db.deleteEmployee(id);
        startActivity(new Intent(this, ListActivity.class));

        Toast.makeText(this, "Delete " + e.getId() + " completed", Toast.LENGTH_SHORT).show();
    }

}
