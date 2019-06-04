/*
 * Copyright (c) 2019 Hai Zhang <dreaming.in.code.zh@gmail.com>
 * All Rights Reserved.
 */

package me.zhanghai.java.reflected;

import androidx.annotation.NonNull;

import java.util.Objects;

abstract class ReflectedMember<T, M> extends ReflectedObject<M> {

    @NonNull
    private final Object mDeclaringClass;

    public ReflectedMember(@NonNull Class<T> declaringClass) {
        mDeclaringClass = Objects.requireNonNull(declaringClass);
    }

    public ReflectedMember(@NonNull ReflectedClass<T> declaringClass) {
        mDeclaringClass = Objects.requireNonNull(declaringClass);
    }

    public ReflectedMember(@NonNull String declaringClassName) {
        this(new ReflectedClass<>(declaringClassName));
    }

    @NonNull
    protected Class<T> getDeclaringClass() {
        if (mDeclaringClass instanceof Class) {
            //noinspection unchecked
            return (Class<T>) mDeclaringClass;
        } else if (mDeclaringClass instanceof ReflectedClass) {
            //noinspection unchecked
            return ((ReflectedClass<T>) mDeclaringClass).get();
        } else {
            throw new AssertionError(mDeclaringClass);
        }
    }
}
