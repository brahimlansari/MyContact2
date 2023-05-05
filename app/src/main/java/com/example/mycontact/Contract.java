package com.example.mycontact;

import android.net.Uri;
import android.provider.BaseColumns;

public final class Contract {

    public Contract(){

    }
    public static final String CONTENT_AUTHORITY = "com.example.mycontacts";
    public static final Uri Base_URI = Uri.parse("content//"+ CONTENT_AUTHORITY);
    public static final String PATH_CONTACTS = "mycontacts";
    public static abstract class ContactEntry implements BaseColumns{
        public static final Uri CONTENT_URI =  Uri.withAppendedPath(Base_URI,PATH_CONTACTS);
        public static final String TABLE_NAME = " mycontacts";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_EMAIL = " email";
        public static final String COLUMN_PICTURE = "picture";
        public static final String COLUMN_PHONENUMBER = "number";
        public static final String COLUMN_TYPEOFCONTACT = "type ";

        public static final String TYPECONTACT_WORK = "work";
        public static final String TYPECONTACT_HOME = "home";
        public static final String TYPECONTACT_PERSONAL = "Personal";

        public static boolean isValidType (String type){
            if (type == TYPECONTACT_HOME || type == TYPECONTACT_PERSONAL || type == TYPECONTACT_WORK){
                return true ;
            }
            return false ;
        }
    }
}
