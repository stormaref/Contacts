package com.sa.contacts;

import com.orm.SugarRecord;

public class Contact extends SugarRecord {
    private String FirstName;
    private String LastName;
    private String Address;
    private String Website;
    private String Mobile;
    private String Home;
    private String Work;
    private String HomeMail;
    private String WorkMail;
    private byte[] Image;
    private boolean isFav;
    private boolean isBlocked;

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
        isFav = false;
        isBlocked = false;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public boolean isFav() {
        return isFav;
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
