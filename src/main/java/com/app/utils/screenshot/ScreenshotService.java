package com.app.utils.screenshot;


import com.microsoft.playwright.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class ScreenshotService {


    @Value("${screenshots.path}")
    private String screenShotsBasePath;

    public String getBase64Screenshot(Page page) {
        byte[] screenshotBytes = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
        return Base64.getEncoder().encodeToString(screenshotBytes);
    }
}
