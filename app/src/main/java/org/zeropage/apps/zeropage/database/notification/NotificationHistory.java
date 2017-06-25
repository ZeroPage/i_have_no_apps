package org.zeropage.apps.zeropage.database.notification;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.zeropage.apps.zeropage.notification.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationHistory {
    private static NotificationHistory sInstance;
    private SQLiteDatabase mDatabase;

    public NotificationHistory getInstance(Context context) {
        sInstance = new Notifi
    }

    private NotificationHistory(Context context) {
        mDatabase = new NotificationOpenHelper(context).getWritableDatabase();
    }

    public void addToHistory(Notification newNotification) {
        ContentValues values = NotificationTable.getContentValues(newNotification);
        mDatabase.insert(NotificationTable.NAME, null, values);
    }

    public List<Notification> getAllHistory() {
        List<Notification> notifications = new ArrayList<>();
        try (NotificationCursorWrapper wrapper = queryHistory(null, null)) {
            wrapper.moveToFirst();

            while (!wrapper.isAfterLast()) {
                notifications.add(wrapper.getNotification());
                wrapper.moveToNext();
            }
        }

        return notifications;
    }

    private NotificationCursorWrapper queryHistory(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(NotificationTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null,
                null);

        return new NotificationCursorWrapper(cursor);
    }
}
