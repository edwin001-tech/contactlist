package com.example.masood.simplecontactlist.database;

import com.orm.SugarRecord;

/**
 * Created by masood on 8/12/17.
 */

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
}

