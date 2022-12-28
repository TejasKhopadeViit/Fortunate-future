package com.example.fortunatefuture;

public class Userhelperclass {
    String name, email, phone, password, uid,age;
    public Userhelperclass() {
    }

    public Userhelperclass(String name, String email, String phone, String password, String uid, String age){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password=password;
        this.uid=uid;
        this.age=age;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getuid() {
        return uid;
    }

    public void setuid(String uid) {
        this.uid = uid;
    }
}
