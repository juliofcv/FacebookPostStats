/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artec.apps.facebook.utils;


/**
 *
 * @author imstu
 */
public class URLUtils {
    
    public static String removeDiagonal(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length()-1)=='/') {
            str = str.substring(0, str.length()-1);
        }
        return str;
    }
    
}