/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artec.apps.facebook.utils;

import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONUtils {
    
    public static JSONObject readJSon (String requestURL) throws Exception {
        
        URL url = new URL(requestURL);
        Scanner scanner = new Scanner(url.openStream());
        String response = scanner.useDelimiter("\\n").next();
        
	scanner.close();
        JSONParser parser = new JSONParser();
        
        Object obj = parser.parse(response);
	JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;
    }
    
}