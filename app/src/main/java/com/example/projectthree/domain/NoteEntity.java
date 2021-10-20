package com.example.projectthree.domain;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteEntity {
    private String id;
    private String title;
    private String detail;
    private final Date currentDate;
    private Date modifiedDate;

    public NoteEntity(){
        title = "";
        detail = "";
        currentDate = new Date();
        generateId();
    }

    // для тестов
    public NoteEntity(String title, String detail) {
        this.title = title;
        this.detail = detail;
        currentDate = new Date();
        generateId();
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    @SuppressLint("SimpleDateFormat")
    private void generateId(){
        id = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    }


    @SuppressLint("SimpleDateFormat")
    public void setModifiedDate(){
        modifiedDate = new Date();
    }
}
