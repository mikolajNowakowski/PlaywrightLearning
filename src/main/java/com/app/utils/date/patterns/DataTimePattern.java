package com.app.utils.date.patterns;

public enum DataTimePattern {

    DDMMYY("dd-mm-rr"),
    HHMMSSDDMMYY("HH:mm:ss  dd-MM-yy");

    private final String pattern;

    DataTimePattern(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
