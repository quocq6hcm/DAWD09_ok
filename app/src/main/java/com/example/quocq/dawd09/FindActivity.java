package com.example.quocq.dawd09;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FindActivity extends AppCompatActivity {

    EditText txtId;
    EditText txtTen;
    EditText txtNgaySinh;
    EditText txtEmail;

    EditText txtQuery;

    TextView lblInfo;

    EmployeeHelper db = new EmployeeHelper(this);

    Employee e = new Employee();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        init();

    }

    void init(){
        this.txtId = (EditText) findViewById(R.id.txtId);
        this.txtEmail = (EditText) findViewById(R.id.txtEmail);
        this.txtNgaySinh = (EditText) findViewById(R.id.txtNgaySinh);
        this.txtTen = (EditText) findViewById(R.id.txtTen);

        this.txtQuery = (EditText) findViewById(R.id.txtQuery);

        this.lblInfo = (TextView) findViewById(R.id.lblInfo);

        this.txtId.setEnabled(false);
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

    public void doFind(View view)
    {
        for (Employee e : db.findAll())
        {
            if(e.getId().equals(txtQuery.getText().toString()))
            {
                this.e = e;
            }
        }
        if (TextUtils.isEmpty(e.getId()))
            this.lblInfo.setText("Not found");
        else
        {
            this.txtId.setText(this.e.getId());
            this.txtTen.setText(this.e.getTen());
            this.txtNgaySinh.setText(this.e.getNgaysinh());
            this.txtEmail.setText(this.e.getEmail());
        }
    }


}
