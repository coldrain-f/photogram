package com.cos.controllerdemo.util;

public class Script {

    public static String back(String message) {
        return "<script>" +
                    "alert('" + message + "');" +
                    "history.back();" +
                "</script>";
    }
}
