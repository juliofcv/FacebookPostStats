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

import static edu.artec.apps.facebook.utils.JSONUtils.readJSon;
import org.json.JSONException;

public class FacebookPageUtils {
    
    private final static String graphUrl = "https://graph.facebook.com/";
    
    private String NAME;
    private String ID;
    private int FANCOUNT;

    public String getNAME() {
        return NAME;
    }

    public String getID() {
        return ID;
    }
    
    public int getFANCOUNT() { return FANCOUNT; }
    
    public FacebookPageUtils(String fbPageUser, String API_VERSION, String token) throws Exception {
        fbPageUser = fbPageUser.replace("https://www.facebook.com/", "");
        org.json.simple.JSONObject json1 = readJSon(graphUrl+API_VERSION+ "/https://www.facebook.com/"+fbPageUser+"?access_token="+token);
        org.json.simple.JSONObject json2 = readJSon(graphUrl+API_VERSION+ "/https://www.facebook.com/"+fbPageUser+"?fields=fan_count&access_token="+token);
        org.json.JSONObject obj = new org.json.JSONObject(json1.toString());
        org.json.JSONObject obj2 = new org.json.JSONObject(json2.toString());
        System.out.println(json2.toString());
        node(obj);
        node2(obj2);
    }
    
    private void node(org.json.JSONObject obj) throws JSONException {
        NAME = obj.getString("name");
        ID = obj.getString("id");
    }

    private void node2(org.json.JSONObject obj) throws JSONException {
        FANCOUNT = obj.getInt("fan_count");
    }
    
}
