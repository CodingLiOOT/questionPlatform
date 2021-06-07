package com.bjtu.questionPlatform.entity;

public class Manager {
    private String managerId;
    private String password;
    private String mail;
    private String phone;

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getMail() {
        return mail;
    }


    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getManagerId() {
        return managerId;
    }
}
