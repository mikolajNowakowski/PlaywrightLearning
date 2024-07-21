package com.app.utils.popups;

import com.microsoft.playwright.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PopupsManager {

    private HashMap<String, Page> pages = new HashMap<>();

    public void addPage(String key, Page page){
        pages.put(key,page);
    }

    public Page getPage(String key){
       return pages.get(key);
    }
    public void deletePage(String key){
        pages.remove(key);
    }

}
