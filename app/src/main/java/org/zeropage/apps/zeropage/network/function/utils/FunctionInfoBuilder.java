package org.zeropage.apps.zeropage.network.function.utils;


import android.support.annotation.NonNull;

import org.zeropage.apps.zeropage.network.NetworkUrl;
import org.zeropage.apps.zeropage.network.function.FunctionType;
import org.zeropage.apps.zeropage.network.utils.RequestInfoBuilder;

public class FunctionInfoBuilder extends RequestInfoBuilder {

    @NonNull
    public RequestInfoBuilder setFunctionType(@NonNull FunctionType type) {
        setBaseUrl(NetworkUrl.FUNCTION);
        setAdditionalUrl(type.getFunctionName());
        return this;
    }
}
