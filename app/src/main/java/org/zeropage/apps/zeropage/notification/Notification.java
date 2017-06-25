package org.zeropage.apps.zeropage.notification;


import android.support.v4.util.Pair;

import java.util.Date;

public class Notification {
    private Date mDate;
    private Pair<String, String> titleBody;

    public Notification(Date date, String title, String body) {
        mDate = date;
        titleBody = new Pair<>(title, body);
    }

    public Date getDate() {
        return mDate;
    }

    public String getTitle() {
        return titleBody.first;
    }

    public String getBody() {
        return titleBody.second;
    }

    @Override
    public String toString() {
        return  "Date : " + mDate.toString() + "\n" +
                "Title : " + getTitle() + "\n" +
                "Body : " + getBody();
    }
}
