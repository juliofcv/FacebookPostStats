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

import edu.artec.apps.facebook.pojo.DataInsights;
import edu.artec.apps.facebook.utils.FacebookPageUtils;
import static edu.artec.apps.facebook.utils.JSONUtils.readJSon;
import edu.artec.apps.facebook.utils.StrUtils;
import edu.artec.apps.facebook.utils.Translator;
import edu.artec.apps.facebook.utils.Translator.Language;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;


/**
 *
 * @author Julio Chinchilla
 */
public class PostInsights {
    
    private final static String graphUrl = "https://graph.facebook.com/";
    private final static String insightsPostVideo = "/insights/"
            
            ;
    
    private List<DataInsights> data;

    public List<DataInsights> getData() {
        return data;
    }

    public PostInsights (String API_VERSION, String token, String urlPost, String metric) throws Exception {
        urlPost = StrUtils.removeLastCharacterAt(urlPost, '/');
        List<String> urlDir  = StrUtils.urlDir(urlPost);
        String idPost = String.valueOf(Long.parseLong(urlDir.get(urlDir.size()-1)));
        String userPage = urlDir.get(urlDir.size()-3);
        FacebookPageUtils fbPg = new FacebookPageUtils(userPage, API_VERSION, token);
        String idPage = fbPg.getID();
        request(API_VERSION,token,idPage,idPost, metric);
          
    }

    private synchronized void request(String API_VERSION, String token, String idPage, String idPost, String metric) throws Exception {
        String requestURLPost = graphUrl+API_VERSION+"/"+idPage+"_"+idPost+insightsPostVideo+metric+"?access_token="+token;
        System.out.println(requestURLPost);
        JSONObject json = readJSon(requestURLPost);
        org.json.JSONObject obj = new org.json.JSONObject(json.toString());
        node(obj);
    }
    
    private synchronized void node(org.json.JSONObject obj) throws JSONException {
        JSONArray root = obj.getJSONArray("data");
        final Translator translator = new Translator(Language.EN, Language.ES, Translator.NAME_TEMPLATE);
        this.data = new ArrayList<DataInsights>();
        for (int i = 0; i < root.length(); ++i) {
            DataInsights pojoData = new DataInsights();
            org.json.JSONObject data = root.getJSONObject(i);
            String dataTitle = data.get("title").toString();
            String dataName = data.get("name").toString();
            String description = Arrays.toString(translator.translate((String) data.get("name")));
            description = description.substring(1, description.length()-1);
            pojoData.setTitle(dataTitle);
            pojoData.setDescription(description);
            pojoData.setName(dataName);
            JSONArray values = data.getJSONArray("values");
            List<String> valuesPojo = new ArrayList<String>();
            for (int k = 0; k < values.length(); k++) {
                org.json.JSONObject jObjValue = values.getJSONObject(k);
                Object value = jObjValue.get("value");
                Object endTime = "";
                try {
                    endTime = jObjValue.get("end_time");
                } catch(Exception e) {}
                if(value.toString().charAt(0) == '{') {
                    String objectValue = value.toString();
                    objectValue = objectValue.substring(1, objectValue.length()-1);
                    List<String> finalValues = csvLine(objectValue);
                    for(String v : finalValues) {
                        valuesPojo.add(v);
                    }
                } else {
                    valuesPojo.add(value.toString());
                    try {
                        if (!endTime.toString().isEmpty()) {
                        valuesPojo.add(endTime.toString());
                        }
                        
                    } catch(Exception e) {} 
                    
                }
                pojoData.setValues(valuesPojo);
            }
            this.data.add(pojoData);
        }
    }
    
    public synchronized  List<String> csvLine(String str) {
        return Arrays.asList(str.split("\\s*,\\s*"));
    }
    
}