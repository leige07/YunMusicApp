/*
 * Copyright (c) 2010, Snowball Finance and/or its affiliates. All rights reserved.
 * SNOWBALLFINANCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.ygsoft.lib_network;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author snoop.fy@gmail.com
 * @version Gsons 1.0.0 Sep 04, 2014 21:04
 * @since 1.0.0 Sep 04, 2014 21:04
 */
public class Gsons {

    private static final String TAG = "Gsons";

    private static Gson gson = null;

    public static Gson defaultGson() {
        if (gson == null) {
            GsonBuilder builder = new GsonBuilder();
            builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
//            builder.registerTypeAdapter(Date.class, DateTypeAdapter);
//            builder.registerTypeAdapter(Calendar.class, CalendarTypeAdapter);
//            builder.registerTypeAdapter(GregorianCalendar.class, CalendarTypeAdapter);
            builder.registerTypeAdapter(double.class, DoubleTypeAdapter);
            builder.registerTypeAdapter(Double.class, DoubleTypeAdapter);
            builder.registerTypeAdapter(int.class, IntegerTypeAdapter);
            builder.registerTypeAdapter(Integer.class, IntegerTypeAdapter);

            gson = builder.excludeFieldsWithoutExposeAnnotation().create();
        }
        return gson;
    }

//    private static TypeAdapter<Date> DateTypeAdapter = new TypeAdapter<Date>() {
//        @Override
//        public void write(JsonWriter out, Date value) throws IOException {
//            out.value(value != null ? value.getTime() : 0);
//        }
//
//        @Override
//        public Date read(JsonReader in) throws IOException {
//            if (in.peek() == JsonToken.NULL) {
//                in.nextNull();
//                return null;
//            }
//            try {
//                String result = in.nextString();
//                if ("".equals(result) || "null".equalsIgnoreCase(result)) {
//                    return null;
//                }
//                if (result.matches("^[0-9]+$")) {
//                    return new Date(Long.parseLong(result));
//                }
//                return DateUtil.parseToCalendar(result).getTime();
//            } catch (Exception e) {
//                LogUtil.e(TAG, "parse date fail" + e.getMessage());
//                return null;
//            }
//        }
//    };

//    private static TypeAdapter<Calendar> CalendarTypeAdapter = new TypeAdapter<Calendar>() {
//        @Override
//        public void write(JsonWriter out, Calendar value) throws IOException {
//            out.value(value != null ? value.getTime().toString() : null);
//        }
//
//        @Override
//        public Calendar read(JsonReader in) throws IOException {
//            if (in.peek() == JsonToken.NULL) {
//                in.nextNull();
//                return null;
//            }
//
//            try {
//                String result = in.nextString();
//                if ("".equals(result)
//                        || "null".equalsIgnoreCase(result)
//                        || "0".equals(result)) {
//                    return null;
//                }
//
//                return DateUtil.parseToCalendar(result);
//            } catch (Exception e) {
//                LogUtil.e(TAG, "parse calendar fail" + e.getMessage());
//                return null;
//            }
//        }
//    };

    private static TypeAdapter<Number> DoubleTypeAdapter = new TypeAdapter<Number>() {
        @Override
        public void write(JsonWriter out, Number value) throws IOException {
            out.value(value);
        }

        @Override
        public Number read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                String result = in.nextString();
                if ("".equals(result) || "null".equalsIgnoreCase(result)) {
                    return null;
                }
                return Double.parseDouble(result);
            } catch (Exception e) {
                return null;
            }
        }
    };

    private static TypeAdapter<Number> IntegerTypeAdapter = new TypeAdapter<Number>() {
        @Override
        public void write(JsonWriter out, Number value) throws IOException {
            out.value(value);
        }

        @Override
        public Number read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            try {
                String result = in.nextString();
                if ("".equals(result) || "null".equalsIgnoreCase(result) || "0.0".equalsIgnoreCase(result)) {
                    return null;
                }
                return Integer.parseInt(result);
            } catch (Exception e) {
                return null;
            }
        }
    };
}
