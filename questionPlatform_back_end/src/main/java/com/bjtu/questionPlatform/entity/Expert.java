package com.bjtu.questionPlatform.entity;

import lombok.Data;

@Data
public class Expert {
    String expertName;
    String keysID;
    String password;
    String expertType;
    String mail;
    String phone;


    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public void setExpertType(String expertType) {
        this.expertType = expertType;
    }

    public void setKeysID(String keysID) {
        this.keysID = keysID;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getExpertName() {
        return expertName;
    }

    public String getExpertType() {
        return expertType;
    }

    public String getKeysID() {
        return keysID;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }
}
