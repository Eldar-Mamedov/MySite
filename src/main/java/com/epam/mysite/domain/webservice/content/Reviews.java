package com.epam.mysite.domain.webservice.content;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Reviews {
    private Date date;
    private String message;
    @SerializedName("rating_mark")
    private int ratingMark;
    @SerializedName("name")
    private String authorName;
    @SerializedName("surname")
    private String authorSurname;

    public Reviews() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRatingMark() {
        return ratingMark;
    }

    public void setRatingMark(int ratingMark) {
        this.ratingMark = ratingMark;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }
}
