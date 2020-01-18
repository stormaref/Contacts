package com.sa.contacts;

import com.orm.SugarRecord;

public class Contact extends SugarRecord {
    String FirstName;
    String LastName;
    String Address;
    String Website;
    String Mobile;
    String Home;
    String Work;
    String HomeMail;
    String WorkMail;
    byte[] Image;
    boolean isfav;

    public Contact() {
    }

    public Contact(String firstName, String lastName, String address, String website, String mobile, String home, String work, String homeMail, String workMail, byte[] image) {
        FirstName = firstName;
        LastName = lastName;
        Address = address;
        Website = website;
        Mobile = mobile;
        Home = home;
        Work = work;
        HomeMail = homeMail;
        WorkMail = workMail;
        Image = image;
        isfav = false;
    }

    public void setFav() {
        this.isfav = true;
    }

    public boolean isFav() {
        return isfav;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getAddress() {
        return Address;
    }

    public String getWebsite() {
        return Website;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getHome() {
        return Home;
    }

    public String getWork() {
        return Work;
    }

    public String getHomeMail() {
        return HomeMail;
    }

    public String getWorkMail() {
        return WorkMail;
    }

    public byte[] getImage() {
        return Image;
    }
}
