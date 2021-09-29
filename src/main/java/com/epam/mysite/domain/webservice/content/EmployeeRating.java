package com.epam.mysite.domain.webservice.content;

import com.google.gson.annotations.SerializedName;

public class EmployeeRating {
    @SerializedName("employee_id")
    private int employeeId;
    @SerializedName("review_id")
    private int reviewId;
    @SerializedName("one_mark")
    private int oneMark;
    @SerializedName("two_mark")
    private int twoMark;
    @SerializedName("three_mark")
    private int threeMark;
    @SerializedName("four_mark")
    private int fourMark;
    @SerializedName("five_mark")
    private int fiveMark;

    public EmployeeRating() {
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getOneMark() {
        return oneMark;
    }

    public void setOneMark(int oneMark) {
        this.oneMark = oneMark;
    }

    public int getTwoMark() {
        return twoMark;
    }

    public void setTwoMark(int twoMark) {
        this.twoMark = twoMark;
    }

    public int getThreeMark() {
        return threeMark;
    }

    public void setThreeMark(int threeMark) {
        this.threeMark = threeMark;
    }

    public int getFourMark() {
        return fourMark;
    }

    public void setFourMark(int fourMark) {
        this.fourMark = fourMark;
    }

    public int getFiveMark() {
        return fiveMark;
    }

    public void setFiveMark(int fiveMark) {
        this.fiveMark = fiveMark;
    }
}
