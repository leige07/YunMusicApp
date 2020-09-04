/*
 * @(#)CC.java 2011-12-16
 *
 * Copyright (c) 2010, Snowball Finance and/or its affiliates. All rights reserved.
 * SNOWBALLFINANCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ygsoft.lib_network;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

/**
 * @param <T>
 * @author caishuhua
 * @verion 0.1.0
 */
public interface SNBFParser<T> {

    T parse(String content);
}
