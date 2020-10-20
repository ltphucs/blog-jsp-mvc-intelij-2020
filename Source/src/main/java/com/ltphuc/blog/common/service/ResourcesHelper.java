package com.ltphuc.blog.common.service;

import java.util.ResourceBundle;

public class ResourcesHelper {
    private ResourceBundle rb ;

    public ResourcesHelper() {
    }

    public String getValue(String key) {
        String value = rb.getString(key);
        return value;
    }

    public ResourcesHelper(String resourceBundle) {
        rb= ResourceBundle.getBundle(resourceBundle);
    }
}
