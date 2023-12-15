package com.cleverpine.exceldatasync.util;


import com.cleverpine.exceldatasync.exception.ReflectionException;

public final class ReflectionUtils {

    private ReflectionUtils() {
        // Should not instantiate this class
    }

    public static <T> T createInstance(Class<T> aClass, String instanceName) {
        try {
            return aClass.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new ReflectionException(String.format(Constants.FAILED_TO_INSTANTIATE, instanceName), e);
        }
    }
}
