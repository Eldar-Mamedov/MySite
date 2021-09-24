package com.epam.mysite.entity.content;

import com.google.gson.annotations.SerializedName;

public class ServiceSubcategory {
    private int id;
    @SerializedName(value = "category_id")
    private int categoryId;
    @SerializedName(value = "subcategory_name")
    private String subcategoryName;
    private String locale;

    public ServiceSubcategory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
