package com.app.utils.date.patterns;

public enum DataTimePattern {

    DDMMYY("dd-mm-rr"),
    HHMMSSDDMMYY("HH:mm:ss  dd-MM-yy"),
    HHMMSSDDMMYY_TO_FILE_NAME("HH-mm-ss__dd-MM-yy");

    private final String pattern;

    DataTimePattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
