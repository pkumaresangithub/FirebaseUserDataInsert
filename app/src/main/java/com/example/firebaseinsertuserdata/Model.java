package com.example.firebaseinsertuserdata;

public class Model {
    String id,username,emailid,contact,password,dob;

    public Model() {
    }

    public Model(String id, String username, String emailid, String contact, String password, String dob) {
        this.id = id;
        this.username = username;
        this.emailid = emailid;
        this.contact = contact;
        this.password = password;
        this.dob = dob;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
