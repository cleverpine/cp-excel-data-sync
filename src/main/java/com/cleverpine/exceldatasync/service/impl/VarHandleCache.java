package com.cleverpine.exceldatasync.service.impl;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VarHandleCache {

    private final Map<Class<?>, Field[]> fieldsCache = new ConcurrentHashMap<>();
    private final Map<Class<?>, Map<String, VarHandle>> varHandleCache = new ConcurrentHashMap<>();

    public Field[] getDeclaredFields(Class<?> dtoClass) {
        return fieldsCache.computeIfAbsent(dtoClass, this::initializeFields);
    }

    public VarHandle getVarHandle(Class<?> dtoClass, String fieldName) {
        return varHandleCache.computeIfAbsent(dtoClass, this::initializeVarHandles).get(fieldName);
    }

    private Field[] initializeFields(Class<?> dtoClass) {
        return dtoClass.getDeclaredFields();
    }

    private Map<String, VarHandle> initializeVarHandles(Class<?> dtoClass) {
        Field[] declaredFields = getDeclaredFields(dtoClass);
        Map<String, VarHandle> fieldVarHandles = new HashMap<>();
        for (Field field : declaredFields) {
            fieldVarHandles.put(field.getName(), createVarHandle(dtoClass, field));
        }
        return fieldVarHandles;
    }

    private VarHandle createVarHandle(Class<?> dtoClass, Field field) {
        try {
            var lookup = MethodHandles.privateLookupIn(dtoClass, MethodHandles.lookup());
            return lookup.findVarHandle(dtoClass, field.getName(), field.getType());
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to initialize VarHandle for " + dtoClass.getName() + "." + field.getName(), e);
        }
    }
}
