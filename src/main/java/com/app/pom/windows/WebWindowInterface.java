package com.app.pom.windows;

import com.microsoft.playwright.Page;

public interface WebWindowInterface {
    void setPageAsFront();
    void closeWindow();
}
