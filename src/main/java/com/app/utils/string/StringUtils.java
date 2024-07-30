package com.app.utils.string;

public class StringUtils {

    public static String removeSpecialCharsFromString(String inputString) {
        return inputString.replaceAll("[^a-zA-Z0-9]", "");
    }

}
