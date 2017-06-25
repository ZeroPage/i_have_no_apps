package org.zeropage.apps.zeropage.data_singleton;

import android.support.annotation.NonNull;

/**
 * Created by gnidoc327 on 2017. 6. 25..
 */

public class User {
    private static final User ourInstance = new User();

    private String nickname;
    private String rank;

    public static User getInstance() {
        return ourInstance;
    }

    private User() {
        nickname = null;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(@NonNull String nickname) {
        this.nickname = nickname;
    }

    public void logout(){
        this.nickname = null;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
