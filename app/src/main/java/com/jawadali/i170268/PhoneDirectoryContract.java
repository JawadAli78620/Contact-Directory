package com.jawadali.i170268;

import android.provider.BaseColumns;

public class PhoneDirectoryContract {

    private PhoneDirectoryContract(){

    }

    public static class ContactEntry implements BaseColumns {
        public static final String TABLE_NAME = "contacts";
        public static final String NAME_COL = "name";
        public static final String PHONE_COL = "phone";
        public static final String ADDRESS_COL = "address";
        public static final String EMAIL_COL = "email";
        public static final String IMAGE_URI_COL = "image_uri";
    }
}
