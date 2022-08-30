package com.example.ito5046;


public class Singleton {
    private static String LOGGEDIN;
    private static String BATTLENAME;

    public static void addValue(String value) {
        LOGGEDIN = value;
    }

    public static String getValue() {
        return LOGGEDIN;
    }

    public static String replaceValue(String username) {
        LOGGEDIN = username;
        return LOGGEDIN;
    }

    public static void addBattle(String value) {
        BATTLENAME = value;
    }

    public static String getBattle() {
        return BATTLENAME;
    }

    public static String replaceBattle(String username) {
        BATTLENAME = username;
        return BATTLENAME;
    }

}

