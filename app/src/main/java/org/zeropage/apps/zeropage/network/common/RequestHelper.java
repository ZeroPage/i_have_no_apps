package org.zeropage.apps.zeropage.network.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.annimon.stream.Stream;
import com.annimon.stream.function.FunctionalInterface;

import java.lang.reflect.Method;

import retrofit2.Call;

final class RequestHelper {
    private static final String TAG = RequestHelper.class.getSimpleName();
    private static final String IMPROPER_CLASS_MESSAGE
            = "The class doesn't have a baseUrl field.";
    private static final String BASE_URL_FIELD_NAME = "BASE_URL";

    @Nullable
    static <T> String getBaseUrl(@NonNull Class<T> classInfo) {
        try {
            return (String) classInfo.getField(BASE_URL_FIELD_NAME).get(null);
        } catch (Exception exception) {
            Log.e(TAG, "An error occurred while accessing to baseUrl. Class = " + classInfo.getSimpleName());
            throw new IllegalArgumentException(IMPROPER_CLASS_MESSAGE);
        }
    }

    private static <T> boolean hasFunctionalAnnotation(Class<T> classInfo) {
        return Stream.of(classInfo.getAnnotations())
                .filter(annotation -> annotation.annotationType().equals(FunctionalInterface.class))
                .findFirst()
                .isPresent();
    }

    private static <T> boolean hasBaseUrl(Class<T> classInfo) {
        try {
            getBaseUrl(classInfo); // Try to get a baseUrl of the class.
            return true;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    private static <T> boolean hasQueueReturnedMethod(Class<T> classInfo) {
        Method method = classInfo.getMethods()[0];
        return method.getReturnType().equals(Call.class);
    }

    @Deprecated
    static <T> boolean isProperRequestClass(@NonNull Class<T> classInfo) {
        return hasFunctionalAnnotation(classInfo) &&
                hasBaseUrl(classInfo) &&
                hasQueueReturnedMethod(classInfo);
    }

    private RequestHelper() {

    }
}
