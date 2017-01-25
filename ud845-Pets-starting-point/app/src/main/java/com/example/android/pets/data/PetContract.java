package com.example.android.pets.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Shomari Malcolm on 2017-01-19.
 */

public final class PetContract {

    private PetContract(){}

    public static final String CONTENT_AUTHORITY = "com.example.android.pets";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_PETS = "pets";

    public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_PETS);

    public static final class PetEntry implements BaseColumns{

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of pets.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS;

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_PETS);

        public static final String TABLE_NAME= "Pets";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PET_NAME ="name";
        public static final String COLUMN_PET_BREED ="breed";
        public static final String COLUMN_PET_GENDER ="gender";
        public static final String COLUMN_PET_WEIGHT ="weight";

        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;

        public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME +" (" + _ID + " INTEGER, " + COLUMN_PET_NAME + " TEXT, " + COLUMN_PET_BREED + " TEXT, " + COLUMN_PET_GENDER + " INTEGER, " + COLUMN_PET_WEIGHT + " INTEGER);";
    }
}
