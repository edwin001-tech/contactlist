package com.example.eduin.simplecontactlist.database;



public class contactModel {
    private String contactName;
    private String contactDetails;
    private String contactNumber;

    public contactModel(String contactName, String contactDetails, String contactNumber) {
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
