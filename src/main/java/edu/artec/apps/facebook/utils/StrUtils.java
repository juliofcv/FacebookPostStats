/* 
 * Facebook Post Stats Public Version 0.1a
 * 
 * 30/03/2019
 *
 * Copyright 2013-2018 GIGADATTA, S.A.
 * Julio Francisco Chinchilla Valenzuela
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */
package edu.artec.apps.facebook.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Julio Chinchilla
 */
public class StrUtils {

    public static String removeLastCharacterAt (String str, char c) {
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == c)
            str = str.substring(0, str.length() - 1);
        return str;
    }

    public static String removeFirstCharacterAt (String str, char c) {
        if (str != null && str.length() > 0 && str.charAt(0) == c)
            str = str.substring(0, str.length() - 1);
        return str;
    }
    
    public static String removeFirstAndLastCharacterAt (String str, char c) {
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == c && str.charAt(0) == c)
            str = str.substring(1, str.length() - 1);
        return str;
    }
    
     public static List<String> separing(String value) {
        List<String> sep = new ArrayList<>();
        String quote = "\"";
        if(value.startsWith(quote)) {
            String[]tokens = value.split("\":");
            sep = Arrays.asList(tokens);
        } else
            sep.add(value);
        return sep;
    }
     
    public static List<String> csvLine(String str) {
        return Arrays.asList(str.split("\\s*,\\s*"));
    }
    
    public synchronized static List<String> urlDir(String str) {
        return Arrays.asList(str.split("\\s*/\\s*"));
    }
}