package com.app.utils.popups;

import com.app.annotations.LazyAutowired;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PopupsManager {

    @LazyAutowired
    private Page mainPage;

    private HashMap<String, Page> pages = new HashMap<>();

    public void addPage(String key, Page page){
        pages.put(key,page);
    }

    public Page getPage(String key){
       return pages.get(key);
    }
    public void deletePage(String key){
        pages.remove(key);
        setFocusToMainPage();

    }

    public void setFocusToMainPage(){
        mainPage.bringToFront();
    }

    public Page returnVisiblePage(){
        if(isPageVisible(mainPage)){
            System.out.println("MAIN PAGE JEST WIDOCZNA!!!!");
            return mainPage;
        }

        System.out.println("INNA PAGE JEST WIDOCZNA");

        return pages
                .values()
                .stream()
                .filter(this::isPageVisible)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Unable to find front page."));
    }
    private boolean isPageVisible(Page page) {

            return (Boolean) page.evaluate("document.activeElement == document.body");

    }

}
