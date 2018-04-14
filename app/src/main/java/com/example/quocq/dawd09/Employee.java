package com.example.quocq.dawd09;

/**
 * Created by quocq on 04/14/2018.
 */

public class Employee {

    String ten;
    String id;
    String ngaysinh;
    String email;

    public Employee(String ten, String id, String ngaysinh, String email) {
        this.ten = ten;
        this.id = id;
        this.ngaysinh = ngaysinh;
        this.email = email;
    }

    public Employee() {

    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return  "-" + id + "-" + ten + "-" + ngaysinh + "-" + email ;
    }
}
