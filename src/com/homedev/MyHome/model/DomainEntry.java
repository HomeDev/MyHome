package com.homedev.MyHome.model;

import android.content.ContentValues;

import java.util.Map;

public abstract class DomainEntry<T> {
    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public abstract ContentValues toContentValues();
}
