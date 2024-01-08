package com.cleverpine.exceldatasync.service.impl;

import com.cleverpine.exceldatasync.exception.ReflectionException;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class VarHandleCache {

    // Cache for storing declared fields of DTO classes
    private final Map<Class<?>, Field[]> fieldsCache = new ConcurrentHashMap<>();

    // Cache for storing VarHandles associated with DTO class fields
    private final  Map<Class<?>, Map<String, VarHandle>> varHandleCache = new ConcurrentHashMap<>();

    // Cache for storing MethodHandles.Lookup instances associated with DTO classes
    private final Map<Class<?>, MethodHandles.Lookup> lookupCache = new ConcurrentHashMap<>();

    public Field[] getDeclaredFields(Class<?> dtoClass) {
        return fieldsCache.computeIfAbsent(dtoClass, Class::getDeclaredFields);
    }

    public VarHandle getVarHandle(Class<?> dtoClass, String fieldName) {
        return varHandleCache.computeIfAbsent(dtoClass, this::initializeVarHandles).get(fieldName);
    }

    private Map<String, VarHandle> initializeVarHandles(Class<?> dtoClass) {
        Field[] declaredFields = getDeclaredFields(dtoClass);
        Map<String, VarHandle> fieldVarHandles = new HashMap<>();
        MethodHandles.Lookup lookup = getLookup(dtoClass);

        for (Field field : declaredFields) {
            fieldVarHandles.put(field.getName(), createVarHandle(lookup, dtoClass, field));
        }

        return fieldVarHandles;
    }

    private MethodHandles.Lookup getLookup(Class<?> dtoClass) {
        return lookupCache.computeIfAbsent(dtoClass, cls -> {
            try {
                return MethodHandles.privateLookupIn(cls, MethodHandles.lookup());
            } catch (ReflectiveOperationException e) {
                throw new ReflectionException("Failed to initialize VarHandles for " + cls.getName(), e);
            }
        });
    }

    private VarHandle createVarHandle(MethodHandles.Lookup lookup, Class<?> dtoClass, Field field) {
        try {
            return lookup.findVarHandle(dtoClass, field.getName(), field.getType());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalArgumentException("Failed to initialize VarHandle for " + dtoClass.getName() + "." + field.getName(), e);
        }
    }
}
