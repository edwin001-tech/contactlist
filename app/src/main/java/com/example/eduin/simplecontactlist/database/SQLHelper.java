package com.example.eduin.simplecontactlist.database;



public class SQLHelper {
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Contact.ContactEntry.TABLE_NAME + " (" +
                    Contact.ContactEntry._ID + " INTEGER PRIMARY KEY," +
                    Contact.ContactEntry.COLUMN_NAME_NAME + " TEXT," +
                    Contact.ContactEntry.COLUMN_NAME_NUMBER + " TEXT)";
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Contact.ContactEntry.TABLE_NAME;
}
