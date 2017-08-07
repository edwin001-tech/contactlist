package com.example.masood.simplecontactlist.database;

import android.provider.BaseColumns;

/**
 * Created by masood on 8/3/17.
 */

public final class Contact {
    private Contact() {}

    public static class ContactEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_NUMBER = "number";
    }
}
