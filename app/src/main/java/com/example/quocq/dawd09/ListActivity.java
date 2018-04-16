package com.example.quocq.dawd09;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ListActivity extends AppCompatActivity {
    ListView lvEmployee;


    EmployeeHelper db = new EmployeeHelper(this);
    List<Employee> eList = new ArrayList<>();


//    class EmployeeAdapter extends ArrayAdapter<Employee> {
//
//        public EmployeeAdapter(Context ctx, int textViewResourceId) {
//            super(ctx, textViewResourceId);
//        }
//
//        public EmployeeAdapter() {
//            super(ListActivity.this, android.R.layout.simple_list_item_1, eList);
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            View row = convertView;
//            if (row == null) {
//                LayoutInflater inflater = getLayoutInflater();
//                row = inflater.inflate(R.layout.row, null);
//
//            }
//
//            Employee p = eList.get(position);
//
//            ((TextView) row.findViewById(R.id.id)).setText("ID: " + p.getId());
//            ((TextView) row.findViewById(R.id.ten)).setText("Ten: " + p.getTen());
//            ((TextView) row.findViewById(R.id.ngaySinh)).setText("Ngay Sinh: " + p.getNgaysinh());
//            ((TextView) row.findViewById(R.id.email)).setText("Email: " + p.getEmail());
//
//            return row;
//        }
//
//    }
//


    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            Employee e = db.findAll().get(position); // lấy item được chọn


            String ID = e.getId();
            String ten = e.getTen();
            String ngaySinh = e.getNgaysinh();
            String email = e.getEmail();


            Intent intent = new Intent(getApplicationContext(), EditActivity.class);
            Bundle extras = new Bundle();


            extras.putString("id",ID);
            extras.putString("ten",ten);
            extras.putString("ngaySinh",ngaySinh);
            extras.putString("email",email);



            intent.putExtras(extras);
            startActivity(intent);


        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        init();

    }


    void init()
    {
        lvEmployee = (ListView) findViewById(R.id.lvEmployee);
        eList = db.findAll();

            lvEmployee.setAdapter(new ArrayAdapter<Employee>(this, android.R.layout.simple_list_item_1, eList));
//        EmployeeAdapter lvEmployeeAdapter = new EmployeeAdapter();
//            lvEmployee.setAdapter(lvEmployeeAdapter);

//        lvEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Employee e = db.findAll().get(position);
//
//                String ID = e.getId();
//                String ten = e.getTen();
//                String ngaySinh = e.getNgaysinh();
//                String email = e.getEmail();
//
//
//                Intent intent = new Intent(getApplicationContext(), EditActivity.class);
//                Bundle extras = new Bundle();
//
//
//                extras.putString("id",ID);
//                extras.putString("ten",ten);
//                extras.putString("ngaySinh",ngaySinh);
//                extras.putString("email",email);
//
//
//
//                intent.putExtras(extras);
//                startActivity(intent);
//
//            }
//        });

        lvEmployee.setOnItemClickListener(onListClick);

    }

    private void writeToFile(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("test.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public void doWrite(View view)
    {
        String s = "";
        for( Employee e : db.findAll())
        {
            s+=e.toString()+"\n";
        }
        writeToFile(s, this);
    }

    public void doRead(View view)
    {
        FileInputStream fis;


        this.eList = new ArrayList<>();

        final StringBuffer storedString = new StringBuffer();

        try {
            fis = openFileInput("test.txt");
            DataInputStream dataIO = new DataInputStream(fis);
            String strLine = null;

            while ((strLine = dataIO.readLine()) != null) {
                storedString.append(strLine);
            }

            dataIO.close();
            fis.close();
        }
        catch  (Exception e) {
        }

        Log.i("file data: ", storedString.toString());
        Toast.makeText(this, storedString.toString(), Toast.LENGTH_LONG).show();
        String[] s = storedString.toString().split("-");
        for(int i = 0; i < s.length; i++ ) {
//            Log.i("data: ", "index: " + i + "-" + s[i]);
            if( i%4 == 1)
            {
                Employee e = new Employee();
                e.setId(s[i]);
                e.setTen(s[i+1]);
                e.setNgaysinh(s[i+2]);
                e.setEmail(s[i+3]);

                eList.add(e);

            }
        }

//        lvEmployee.setAdapter(new EmployeeAdapter());
        lvEmployee.setAdapter(new ArrayAdapter<Employee>(this, android.R.layout.simple_list_item_1, eList));
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
}
