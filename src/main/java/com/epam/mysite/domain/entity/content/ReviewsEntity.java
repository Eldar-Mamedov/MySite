package com.epam.mysite.domain.entity.content;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class ReviewsEntity {
    private int id;
    @SerializedName("creation_date")
    private Timestamp date;
    private String message;
    @SerializedName("rating_mark")
    private int ratingMark;
    @SerializedName("name")
    private String authorName;
    @SerializedName("surname")
    private String authorSurname;

    public ReviewsEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
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
