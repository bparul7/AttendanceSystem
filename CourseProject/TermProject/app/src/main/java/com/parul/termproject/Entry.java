package com.parul.termproject;

public class Entry {
    String name;
    String rollNo;
    String category;
    String deptt;

    public Entry () {

    }

    public Entry(String name, String rollNo, String category, String deptt) {
        this.name = name;
        this.rollNo = rollNo;
        this.category = category;
        this.deptt = deptt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDeptt() {
        return deptt;
    }

    public void setDeptt(String deptt) {
        this.deptt = deptt;
    }
}