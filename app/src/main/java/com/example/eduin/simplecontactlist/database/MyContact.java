package com.example.eduin.simplecontactlist.database;

import com.orm.SugarRecord;



public class MyContact extends SugarRecord {
    String contactName;
    String contactDetails;
    String contactNumber;

    public MyContact() {
        this.contactName = "";
        this.contactDetails = "";
        this.contactNumber = "";
    }

    public MyContact(String contactName, String contactDetails, String contactNumber) {
        this.contactName = contactName;
        this.contactDetails = contactDetails;
        this.contactNumber = contactNumber;
    }

    public String getContactName() {
        return  contactName;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactName(String name) {
        this.contactName = name;
    }

    public void setContactDetails(String details) {
        this.contactDetails = details;
    }

    public void setContactNumber(String number) {
        this.contactNumber = number;
    }
}

