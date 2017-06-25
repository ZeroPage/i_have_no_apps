package org.zeropage.apps.zeropage.database.notification;

import android.content.ContentValues;

import org.zeropage.apps.zeropage.notification.Notification;

class NotificationTable {
    static final String NAME = "notification";

    static ContentValues getContentValues(Notification notification) {
        ContentValues values = new ContentValues();
        values.put(NotificationTable.Column.DATE, notification.getDate().getTime());
        values.put(NotificationTable.Column.TITLE, notification.getTitle());
        values.put(NotificationTable.Column.BODY, notification.getBody());

        return values;
    }

    class Column {
        static final String DATE = "date";
        static final String TITLE = "title";
        static final String BODY = "body";
    }
}
