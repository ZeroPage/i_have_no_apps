package org.zeropage.apps.zeropage.database.notification;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class NotificationOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "notification.db";

    NotificationOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + NotificationTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                NotificationTable.Column.DATE + ", " +
                NotificationTable.Column.TITLE + ", " +
                NotificationTable.Column.BODY +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
