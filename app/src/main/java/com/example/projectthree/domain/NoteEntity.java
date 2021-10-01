package com.example.projectthree.domain;

import android.annotation.SuppressLint;

import com.example.projectthree.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class NoteEntity {
    private String id;
    private String title;
    private String detail;
    private String date;

    public NoteEntity(){
        title = "";
        detail = "";
        setCurrentDate();
        generateId();
    }

    // для тестов
    public NoteEntity(String title, String detail) {
        this.title = title;
        this.detail = detail;
        setCurrentDate();
        generateId();
    }

    public String getDate() {
        return date;
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
    private void setCurrentDate(){
        date = new SimpleDateFormat("dd/MM/yyyy - HH.mm.ss").format(new Date());
    }
}
