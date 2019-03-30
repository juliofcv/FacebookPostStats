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
package edu.artec.apps.facebook.insights;

import edu.artec.apps.facebook.pojo.DataPagePosts;
import edu.artec.apps.facebook.utils.FacebookPageUtils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static edu.artec.apps.facebook.utils.JSONUtils.readJSon;

/**
 *
 * @author Julio Chinchilla
 */
public class PagePostData {
    
    private final static String graphUrl = "https://graph.facebook.com/"
            ;
    private final static String requestFBAPI = "/posts?limit=100";
    
    private List<DataPagePosts> data;

    public List<DataPagePosts> getData() {
        return data;
    }
    
    public PagePostData(String API_VERSION, String token, String urlPage, String startDate, String endDate) throws Exception {
        FacebookPageUtils fb = new FacebookPageUtils(urlPage, API_VERSION, token);
        request(API_VERSION, token,fb.getID(), startDate, endDate);
    }
    
    private synchronized void request(String API_VERSION, String token, String idPage, String startDate, String endDate) throws Exception {
        String requestURL = graphUrl+API_VERSION+"/"+idPage+requestFBAPI+"&since="+startDate+"&until="+endDate+"&access_token="+token;
        System.out.println(requestURL);
        boolean cont = true;
        JSONObject json = readJSon(requestURL);
        this.data = new ArrayList<>();
        while(cont) {
            org.json.JSONObject obj = new org.json.JSONObject(json.toString());
            JSONArray root = obj.getJSONArray("data");
            org.json.JSONObject jst = (org.json.JSONObject) obj.get("paging");
            for (int i = 0; i < root.length(); ++i) {                
                org.json.JSONObject dataArray = root.getJSONObject(i);
                String createdTime = dataArray.get("created_time").toString();
                DataPagePosts pojoData = new DataPagePosts();
                String story = "";
                String message = "";
                try {
                    message = dataArray.get("message").toString();
                } catch(JSONException je){}
                try {
                    story = dataArray.get("story").toString();
                } catch(JSONException je){}
                String id = dataArray.get("id").toString();
                pojoData.setId(id);
                String[] parts = id.split("_");
                pojoData.setUrlPost("https://www.facebook.com/"+parts[0]+"/posts/"+parts[1]);
                pojoData.setCreatedTime(createdTime);
                pojoData.setMessage(message);
                pojoData.setStory(story);                
                this.data.add(pojoData); 
            }
            try {
                json = readJSon(jst.get("next").toString());
            } catch (Exception e) {cont = false;}  
        }
    }    

}