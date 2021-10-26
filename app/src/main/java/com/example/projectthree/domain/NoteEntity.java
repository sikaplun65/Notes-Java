package com.example.projectthree.domain;

import java.util.Calendar;

public class NoteEntity {
    private final Calendar createDate;
    private Calendar modifiedDate;
    private String id;
    private String title;
    private String detail;

    public NoteEntity() {
        title = "";
        detail = "";
        createDate = Calendar.getInstance();
        generateId();
    }

    // для тестов
    public NoteEntity(String title, String detail) {
        this.title = title;
        this.detail = detail;
        createDate = Calendar.getInstance();
        generateId();
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public Calendar getModifiedDate() {
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

    private void generateId() {
        id = String.valueOf(Calendar.getInstance().getTime().getTime());
    }

    public void setModifiedDate() {
        modifiedDate = Calendar.getInstance();
    }
}
