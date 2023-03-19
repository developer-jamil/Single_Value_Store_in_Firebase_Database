package com.jamillabltd.singlevaluestoreinfirebase;

public class UserInfo {

    //empty construction - for firebase
    public  UserInfo(){}

    public String nameTitle;


    //construction
    public UserInfo(String nameTitle) {
        this.nameTitle = nameTitle;
    }

    //getter and setter - name
    public String getNameTitle() {
        return nameTitle;
    }

    public void setNameTitle(String nameTitle) {
        this.nameTitle = nameTitle;
    }


}