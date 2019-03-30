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

package edu.artec.apps.facebook;


import edu.artec.apps.facebook.net.Time;
import edu.artec.apps.facebook.pojo.DataInsights;
import edu.artec.apps.facebook.pojo.DataPagePosts;
import edu.artec.apps.facebook.reports.reportOne;
import edu.artec.apps.facebook.utils.DateUtils;
import edu.artec.apps.facebook.utils.FacebookPageUtils;
import edu.artec.apps.facebook.utils.Translator;
import edu.artec.apps.facebook.utils.StrUtils;
import edu.artec.apps.facebook.insights.PagePostData;
import edu.artec.apps.facebook.insights.PostInsights;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Julio Chinchilla
 */
public class FacebookPostStats {
    
    //https://developers.facebook.com/tools/explorer/
    //https://developers.facebook.com/docs/graph-api/reference/v3.2/insights
    
     
     
     // Datos de conexión a Facebook
     private static final String userPage = "USER_PAGE";
     private static final String urlPage = "https://www.facebook.com/"+userPage+"/";
     
     private static final String API_VERSION = "v3.2";    
     private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
     
     private static final String startDate  = "01-03-2019T06:00:00"; 
     private static final String endDate    = "30-03-2019T05:59:59";
     
     private static final String localFilePath = "D:\\";
     
     private static final boolean publish = true;
     
     
     public static void main(String... args) throws Exception {         
         generate (startDate,endDate);
     }
    
    public synchronized static void generate (String startDate, String endDate ) throws ParseException, Exception {
        String tittle = ""; String edate = ""; String stdate = ""; String va = ""; String val = "";
        List<String> dataexp = new ArrayList<>();
        FacebookPageUtils fb = new FacebookPageUtils(userPage, API_VERSION, ACCESS_TOKEN);
        PagePostData pageposts = new PagePostData(API_VERSION, ACCESS_TOKEN, urlPage, startDate, endDate);
        List<String> metric = Parameters.metrics();      
        String metrics = "";
        for(String m:metric)
            metrics = metrics +m+",";        
        metrics = StrUtils.removeLastCharacterAt(metrics, ',');
        //invocación de método para traducción
        final Translator translator = new Translator(Translator.Language.EN, Translator.Language.ES, Translator.NAME_TEMPLATE);
        for(String sm:metric) {
            String description = Arrays.deepToString(translator.translate(sm));
            description = description.substring(1, description.length()-1);
                tittle = tittle + "'"+description+"',";
        }
        boolean edatecount = true;
        List<String> valueSeparated = new ArrayList<>();
        List<DataPagePosts> dataPage = pageposts.getData();
        int cc = 0;
        int sumvalue = 0;
        for (DataPagePosts data: dataPage) {
            PostInsights postInsights = new PostInsights(API_VERSION, ACCESS_TOKEN, data.getUrlPost(), metrics);
            List<DataInsights> dataPost = postInsights.getData();
            stdate = DateUtils.GuatemalaTime(data.getCreatedTime(),"dd/MM/yyyy");
            if(edatecount)
                edate = stdate;
            edatecount = false;
            va = "\t\t\t\t\t['"+stdate+"',  ";
            System.out.println("Explore: "+data.getUrlPost()+" "+stdate);

            for (DataInsights d: dataPost) {
                List<String> values = d.getValues();
                System.out.println("XXX\t"+d.getName());
                for (String value: values) {
                    value = value.startsWith("\"other\":") ? value.substring(8) : value;
                    value = value.equals("") ? "0" : value;
                     va = va + value + ",";
                    System.out.println("XXX\t"+value);
                    if(d.getName().equals("post_impressions_unique")) {
                        sumvalue = sumvalue + Integer.valueOf(value);
                        cc = cc+1;
                        
                           
                    }
                }
            }
            va = va + "'"+data.getUrlPost()+"'";
            va = StrUtils.removeLastCharacterAt(va,',') + "],\n";
            System.out.println("-------------");
            valueSeparated.add(va);
        }
        System.out.println(sumvalue);
        double promedio = sumvalue / cc;
        System.out.println("sumvalue: "+sumvalue);
        System.out.println("cc: "+cc);
        System.out.println("promedio: "+promedio);
        
        Collections.reverse(valueSeparated);
        for(String str: valueSeparated) {
            val = val + str;
        }

        val = StrUtils.removeLastCharacterAt(val,',');
        tittle = tittle + "{ type: 'string', role: 'selection' }],\n";
        System.out.println(tittle);
        String export = tittle+val;
        dataexp.add(export);

        String timeC = Time.getTime(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"));
        String timeD = Time.getTime(new SimpleDateFormat("ddMMyyyyHHmmss"));
        System.out.println("reporte");
        tittle = "Página: " + fb.getNAME() + "\\n" +
                "desde: "+stdate+" hasta: "+edate+ "\\n"
                +"Alcance promedio: "+promedio+ "\\n"
                +"Me gusta a la página: "+fb.getFANCOUNT()+ "\\n"
                +"Porcentaje de alcance: "+Math.round((promedio/Double.valueOf(fb.getFANCOUNT()))*100) +"%"+ "\\n"
                +"reporte generado: "+timeC+ "\\n";
        String localFileReport = localFilePath+userPage+timeD+".html";
        reportOne.generate(dataexp, tittle, localFileReport, String.valueOf(metric.size()+1), publish);

    }


    
}

