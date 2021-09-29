package com.epam.mysite.domain.webservice;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
import java.util.Objects;

public class User {
    private String name;
    private String surname;
    @SerializedName(value = "phone")
    private String phoneNumber;
    private String email;
    private String login;
    private String password;
    private String gender;
    private String role;
    private List<Integer> serviceIds;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return Objects.isNull(surname) ? "" : surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return Objects.isNull(phoneNumber) ? "" : phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Objects.isNull(email) ? "" : email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return Objects.isNull(login) ? "" : login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return Objects.isNull(password) ? "" : password;
    }

    public void setPassword(String password) {
        this.password = DigestUtils.md5Hex(password);
    }

    public String getGender() {
        return Objects.isNull(gender) ? "" : gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Integer> getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(List<Integer> serviceIds) {
        this.serviceIds = serviceIds;
    }
}
