package com.laurelkeys.userinformationtestingrobots.utils;

/**
 * Created by Tiago in July 2016.
 */
public class Utils {

    public class Regex {

        public static final String NAME = "^[\\p{L} .'-]+$";
        public static final String PHONE_NUMBER = "^[A-Za-z0-9_.]+@[A-Za-z0-9_.]+\\.[A-Za-z]{2,3}$";
        public static final String EMAIL = "^[A-Za-z0-9_.]+@[A-Za-z0-9_.]+\\.[A-Za-z]{2,3}$";
        public static final String AGE = "^[0-9]{1,2}$";
    }
}
