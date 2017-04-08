package org.zeropage.apps.zeropage.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;

import java.util.List;

public interface RequestInfo {

    @NonNull
    String getBaseRequestUrl();
    @NonNull
    String getAdditionalUrl();

    /**
     * Get a data of the request, which consist of param/args pair.
     * @return list of the request's param/args pair.
     */
    @Nullable
    List<Pair<String, String>> getRequestData();
}
