package com.app.config.playwright.scopes.page;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.SimpleThreadScope;

public class PageScope extends SimpleThreadScope {

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Object o = super.get(name, objectFactory);

        Page page = ((Page)o);

        if(page.isClosed()){
            super.remove(name);
            o = super.get(name,objectFactory);
        }
        return o;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        super.registerDestructionCallback(name, callback);
    }

}
