package com.example.demo.untils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * @author ctf
 * @site
 * @create 2020-12-02 11:22
 */
@Slf4j
public class PublicKeyUntil {
    @Autowired
    Environment environment;

    public static String replaceKey(String oldKey) {
         char c = oldKey.charAt(33);
        char w = oldKey.charAt(34);
        char[] chars = oldKey.toCharArray();
        chars[34]=c;
        chars[33]=w;
        String newKey="";
        for (char aChar : chars) {
            newKey+=aChar;
        }
        return newKey;
    }
}
