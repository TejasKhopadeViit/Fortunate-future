package com.example.fortunatefuture;

public class Userhelperclass2 {
    String name, email, phone, uid,age,type,amount,status,donated,story,location;
    public Userhelperclass2() {
    }
    public Userhelperclass2(String name, String email, String phone,  String uid, String age, String type, String amount, String status, String donated,String story, String location) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.donated=donated;
        this.uid=uid;
        this.age=age;
        this.type=type;
        this.amount=amount;
        this.status=status;
        this.story=story;
        this.location=location;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDonated() {
        return donated;
    }

    public void setDonated(String donated) {
        this.donated = donated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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



    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
