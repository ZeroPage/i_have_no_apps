package org.zeropage.apps.zeropage.database.notification;

import android.database.Cursor;
import android.database.CursorWrapper;

import org.zeropage.apps.zeropage.notification.Notification;

import java.util.Date;

class NotificationCursorWrapper extends CursorWrapper {
    NotificationCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    Notification getNotification() {
        String title = getString(getColumnIndex(NotificationTable.Column.TITLE));
        String body = getString(getColumnIndex(NotificationTable.Column.BODY));
        long date = getLong(getColumnIndex(NotificationTable.Column.DATE));

        return new Notification(new Date(date), title, body);
    }
}
