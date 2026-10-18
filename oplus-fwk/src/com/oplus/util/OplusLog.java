/*
 * SPDX-FileCopyrightText: The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.oplus.util;

import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;

/**
 * Mirrors the OxygenOS oplus-framework logging helper. Overloads that take a
 * {@code String key} as the first argument only log when that system property is "1".
 */
public class OplusLog {
    private static final String KEY_EXTRA_TAG = "log.tag.extra";

    public OplusLog() {}

    public static String buildMessage(Object... args) {
        if (args == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            if (arg != null) {
                sb.append(arg.toString());
            }
        }
        return sb.toString();
    }

    public static String joinString(String... args) {
        return buildMessage((Object[]) args);
    }

    public static boolean getDebug(String key) {
        return "1".equals(SystemProperties.get(key));
    }

    private static String getExtraTag() {
        String extra = SystemProperties.get(KEY_EXTRA_TAG);
        return TextUtils.isEmpty(extra) ? "" : joinString(extra, ":");
    }

    public static String getTag(Class<?> cls) {
        return joinString(getExtraTag(), cls.getSimpleName());
    }

    public static String getTag(Object obj) {
        return getTag(obj.getClass());
    }

    public static void printStackTrace(StackTraceElement[] stack, String tag, boolean force) {}

    // Verbose

    public static void v(String tag, String msg) {
        Log.v(tag, msg);
    }

    public static void v(String tag, String msg, Throwable tr) {
        Log.v(tag, msg, tr);
    }

    public static void v(boolean on, String tag, String msg) {
        if (on) Log.v(tag, msg);
    }

    public static void v(boolean on, String tag, String msg, Throwable tr) {
        if (on) Log.v(tag, msg, tr);
    }

    public static void v(Class<?> cls, Object... msg) {
        Log.v(getTag(cls), buildMessage(msg));
    }

    public static void v(String key, Class<?> cls, Object... msg) {
        if (getDebug(key)) Log.v(getTag(cls), buildMessage(msg));
    }

    public static void v(String key, Class<?> cls, Throwable tr, Object... msg) {
        if (getDebug(key)) Log.v(getTag(cls), buildMessage(msg), tr);
    }

    public static void v(String key, String tag, Object... msg) {
        if (getDebug(key)) Log.v(tag, buildMessage(msg));
    }

    public static void v(String key, String tag, Throwable tr, Object... msg) {
        if (getDebug(key)) Log.v(tag, buildMessage(msg), tr);
    }

    // Debug

    public static void d(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void d(String tag, String msg, Throwable tr) {
        Log.d(tag, msg, tr);
    }

    public static void d(boolean on, String tag, String msg) {
        if (on) Log.d(tag, msg);
    }

    public static void d(boolean on, String tag, String msg, Throwable tr) {
        if (on) Log.d(tag, msg, tr);
    }

    public static void d(Class<?> cls, Object... msg) {
        Log.d(getTag(cls), buildMessage(msg));
    }

    public static void d(String key, Class<?> cls, Object... msg) {
        if (getDebug(key)) Log.d(getTag(cls), buildMessage(msg));
    }

    public static void d(String key, Class<?> cls, Throwable tr, Object... msg) {
        if (getDebug(key)) Log.d(getTag(cls), buildMessage(msg), tr);
    }

    public static void d(String key, String tag, Object... msg) {
        if (getDebug(key)) Log.d(tag, buildMessage(msg));
    }

    public static void d(String key, String tag, Throwable tr, Object... msg) {
        if (getDebug(key)) Log.d(tag, buildMessage(msg), tr);
    }

    // Info

    public static void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void i(String tag, String msg, Throwable tr) {
        Log.i(tag, msg, tr);
    }

    public static void i(boolean on, String tag, String msg) {
        if (on) Log.i(tag, msg);
    }

    public static void i(boolean on, String tag, String msg, Throwable tr) {
        if (on) Log.i(tag, msg, tr);
    }

    public static void i(Class<?> cls, Object... msg) {
        Log.i(getTag(cls), buildMessage(msg));
    }

    public static void i(String key, Class<?> cls, Object... msg) {
        if (getDebug(key)) Log.i(getTag(cls), buildMessage(msg));
    }

    public static void i(String key, Class<?> cls, Throwable tr, Object... msg) {
        if (getDebug(key)) Log.i(getTag(cls), buildMessage(msg), tr);
    }

    public static void i(String key, String tag, Object... msg) {
        if (getDebug(key)) Log.i(tag, buildMessage(msg));
    }

    public static void i(String key, String tag, Throwable tr, Object... msg) {
        if (getDebug(key)) Log.i(tag, buildMessage(msg), tr);
    }

    // Warn

    public static void w(String tag, String msg) {
        Log.w(tag, msg);
    }

    public static void w(String tag, String msg, Throwable tr) {
        Log.w(tag, msg, tr);
    }

    public static void w(boolean on, String tag, String msg) {
        if (on) Log.w(tag, msg);
    }

    public static void w(boolean on, String tag, String msg, Throwable tr) {
        if (on) Log.w(tag, msg, tr);
    }

    public static void w(Class<?> cls, Object... msg) {
        Log.w(getTag(cls), buildMessage(msg));
    }

    public static void w(String key, Class<?> cls, Object... msg) {
        if (getDebug(key)) Log.w(getTag(cls), buildMessage(msg));
    }

    public static void w(String key, Class<?> cls, Throwable tr, Object... msg) {
        if (getDebug(key)) Log.w(getTag(cls), buildMessage(msg), tr);
    }

    public static void w(String key, String tag, Object... msg) {
        if (getDebug(key)) Log.w(tag, buildMessage(msg));
    }

    public static void w(String key, String tag, Throwable tr, Object... msg) {
        if (getDebug(key)) Log.w(tag, buildMessage(msg), tr);
    }

    // Error

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void e(String tag, String msg, Throwable tr) {
        Log.e(tag, msg, tr);
    }

    public static void e(boolean on, String tag, String msg) {
        if (on) Log.e(tag, msg);
    }

    public static void e(boolean on, String tag, String msg, Throwable tr) {
        if (on) Log.e(tag, msg, tr);
    }

    public static void e(Class<?> cls, Object... msg) {
        Log.e(getTag(cls), buildMessage(msg));
    }

    public static void e(String key, Class<?> cls, Object... msg) {
        if (getDebug(key)) Log.e(getTag(cls), buildMessage(msg));
    }

    public static void e(String key, Class<?> cls, Throwable tr, Object... msg) {
        if (getDebug(key)) Log.e(getTag(cls), buildMessage(msg), tr);
    }

    public static void e(String key, String tag, Object... msg) {
        if (getDebug(key)) Log.e(tag, buildMessage(msg));
    }

    public static void e(String key, String tag, Throwable tr, Object... msg) {
        if (getDebug(key)) Log.e(tag, buildMessage(msg), tr);
    }

    // What a terrible failure

    public static void wtf(String tag, String msg) {
        Log.wtf(tag, msg);
    }

    public static void wtf(String tag, Throwable tr) {
        Log.wtf(tag, tr);
    }

    public static void wtf(boolean on, String tag, String msg) {
        if (on) Log.wtf(tag, msg);
    }

    public static void wtf(boolean on, String tag, Throwable tr) {
        if (on) Log.wtf(tag, tr);
    }

    public static void wtf(String key, Class<?> cls, Object... msg) {
        if (getDebug(key)) Log.wtf(getTag(cls), new Exception(buildMessage(msg)));
    }

    public static void wtf(String key, String tag, Object... msg) {
        if (getDebug(key)) Log.wtf(tag, new Exception(buildMessage(msg)));
    }
}
