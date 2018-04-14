package com.example.quocq.dawd09;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quocq on 04/14/2018.
 */

public class EmployeeHelper extends SQLiteOpenHelper {
    public EmployeeHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "DAWD_Lab09.db";

    private static final String TABLE_NAME = "Employee";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TEN= "ten";
    private static final String COLUMN_NGAYSINH= "ngaysinh";
    private static final String COLUMN_EMAIL= "email";
    String create_table;



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        create_table = "create table " + TABLE_NAME +
                "(" + COLUMN_ID + " text primary key, " +
                COLUMN_TEN + " text," +
                COLUMN_NGAYSINH + " text," +
                COLUMN_EMAIL + " text)";

        sqLiteDatabase.execSQL(create_table);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addEmployee(Employee e) {
        SQLiteDatabase db = getWritableDatabase();
//        Log.i("book data: ", b.toString());
//        Log.i("query: ", create_table);
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, e.getId());
        values.put(COLUMN_EMAIL, e.getEmail());
        values.put(COLUMN_NGAYSINH, e.getNgaysinh());
        values.put(COLUMN_TEN, e.getTen());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Employee> findAll() {
        List<Employee> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {

            do {
                Employee e = new Employee();
                e.setId(cursor.getString(0));
                e.setTen(cursor.getString(1));
                e.setNgaysinh(cursor.getString(2));
                e.setEmail(cursor.getString(3));
                list.add(e);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
//        list.add(new Employee("das", "asd", "123", "asd"));
        return list;
    }

    public void updateEmployee(Employee e)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, e.getId());
        values.put(COLUMN_TEN, e.getTen());
        values.put(COLUMN_EMAIL, e.getEmail());
        values.put(COLUMN_NGAYSINH, e.getNgaysinh());

        SQLiteDatabase db = this.getWritableDatabase();

        db.update(TABLE_NAME, values, COLUMN_ID + " = \"" + e.getId() +"\"", null);  // id là string

        db.close();

    }


    public boolean deleteEmployee(String id)
    {
        boolean result = false;

        String query = "Select * from " + TABLE_NAME + " where " + COLUMN_ID + " = \"" + id + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Employee e = new Employee();

        if(cursor.moveToFirst())
        {
            e.setId(cursor.getString(0));
            db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                    new String[] { e.getId()});

            cursor.close();

            result = true;
        }

        db.close();

        return result;
    }


}
