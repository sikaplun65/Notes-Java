package com.example.projectthree.domain;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteEntity {
    private String id;
    private String title;
    private String detail;
    private String createDate;
    private String modifiedDate;

    public NoteEntity(){
        title = "";
        detail = "";
        modifiedDate ="";
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

    public String getCreateDate() {
        return createDate;
    }

    public String getModifiedDate() {
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
    private void setCurrentDate(){
        createDate = new SimpleDateFormat("dd/MM/yyyy - HH.mm.ss").format(new Date());
    }

    @SuppressLint("SimpleDateFormat")
    public void setModifiedDate(){
        modifiedDate = new SimpleDateFormat("dd/MM/yyyy - HH.mm.ss").format(new Date());
    }
}
