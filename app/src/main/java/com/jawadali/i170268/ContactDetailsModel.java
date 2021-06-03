package com.jawadali.i170268;

import java.io.Serializable;

public class ContactDetailsModel implements Serializable {

    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String ImgUri;


    public ContactDetailsModel(){
        name = "";
        phone = "";
        email = "";
        address = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUri() {
        return ImgUri;
    }

    public void setImgUri(String imgUri) {
        ImgUri = imgUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
