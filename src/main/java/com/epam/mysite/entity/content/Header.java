package com.epam.mysite.entity.content;

import com.google.gson.annotations.SerializedName;

public class Header {
    private int id;
    private int position;
    @SerializedName(value = "header_name")
    private String headerName;
    private String locale;

    public Header() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
