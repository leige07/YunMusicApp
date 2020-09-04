/*
 * Copyright (c) 2010, Snowball Finance and/or its affiliates. All rights reserved.
 * SNOWBALLFINANCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ygsoft.lib_network;
import android.text.TextUtils;

import com.google.gson.JsonObject;
import java.lang.reflect.Type;

/**
 * @author snoop.fy@gmail.com
 * @version GParser 1.0.0 Sep 22, 2014 23:15
 * @since 1.0.0 Sep 22, 2014 23:15
 */
public class GParser<T> implements SNBFParser<T> {

    private String name;

    private Type type;

    public GParser(Type type) {
        this.type = type;
    }

    public GParser(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public T parse(String content) {
        if (!TextUtils.isEmpty(name)) {
            JsonObject obj = Gsons.defaultGson().fromJson(content, JsonObject.class);
            return Gsons.defaultGson().fromJson(obj.get(name), type);
        } else {
            return Gsons.defaultGson().fromJson(content, type);
        }
    }
}
