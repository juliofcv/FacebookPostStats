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