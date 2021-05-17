package com.bjtu.questionPlatform.entity;

import lombok.Data;



public class Expert {
    String expertName;
    String keysId;
    String expertType;
    String expertUnit;
    String expertInformation;
    String mail;

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public String getKeysId() {
        return keysId;
    }

    public void setKeysId(String keysId) {
        this.keysId = keysId;
    }

    public String getExpertType() {
        return expertType;
    }

    public void setExpertType(String expertType) {
        this.expertType = expertType;
    }

    public String getExpertUnit() {
        return expertUnit;
    }

    public void setExpertUnit(String expertUnit) {
        this.expertUnit = expertUnit;
    }

    public String getExpertInformation() {
        return expertInformation;
    }

    public void setExpertInformation(String expertInformation) {
        this.expertInformation = expertInformation;
    }

}
