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
     
     private static final String localFilePath = "D:\\";
     
     private static final boolean publish = true;
     
     
     public static void main(String... args) throws Exception {         
         String startDate = "1-03-2019T06:00:00"; //"II/XX/2018 06:00:00" para comenzar a las 00:00 horas del día II Hora de Guatemala
         String endDate = "30-03-2019T05:59:59"; //"UU/XX/2018 05:59:59" para finalizar a las 23:59 horas del día UU Hora de Guatemala
         generate (startDate,endDate);
         //byYear();
     }
     
     
     public static void byYear () throws Exception {
         
         String year = "2016";
          
          String startDate = "1-01-"+year+"T06:00:00"; //"II/XX/2018 06:00:00" para comenzar a las 00:00 horas del día II
          String endDate = "31-01-"+year+"T05:59:59"; //"UU/XX/2018 05:59:59" para finalizar a las 23:59 horas del día UU
          generate (startDate,endDate);
          
          startDate = "1-02-"+year+"T06:00:00"; //"II/XX/2018 06:00:00" para comenzar a las 00:00 horas del día II
          endDate = "28-02-"+year+"T05:59:59"; //"UU/XX/2018 05:59:59" para finalizar a las 23:59 horas del día UU
          generate (startDate,endDate);
          
          startDate = "1-03-"+year+"T06:00:00"; //"II/XX/2018 06:00:00" para comenzar a las 00:00 horas del día II
          endDate = "31-03-"+year+"T05:59:59"; //"UU/XX/2018 05:59:59" para finalizar a las 23:59 horas del día UU
          generate (startDate,endDate);
          
          startDate = "1-04-"+year+"T06:00:00"; //"II/XX/2018 06:00:00" para comenzar a las 00:00 horas del día II
          endDate = "30-04-"+year+"T05:59:59"; //"UU/XX/2018 05:59:59" para finalizar a las 23:59 horas del día UU
          generate (startDate,endDate);
          
          startDate = "1-05-"+year+"T06:00:00"; //"II/XX/2018 06:00:00" para comenzar a las 00:00 horas del día II
          endDate = "31-05-"+year+"T05:59:59"; //"UU/XX/2018 05:59:59" para finalizar a las 23:59 horas del día UU
          generate (startDate,endDate);
          
          startDate = "1-06-"+year+"T06:00:00"; //"II/XX/2018 06:00:00" para comenzar a las 00:00 horas del día II
          endDate = "30-06-"+year+"T05:59:59"; //"UU/XX/2018 05:59:59" para finalizar a las 23:59 horas del día UU
          generate (startDate,endDate);
          
          startDate = "1-07-"+year+"T06:00:00"; //"II/XX/2018 06:00:00" para comenzar a las 00:00 horas del día II
          endDate = "31-07-"+year+"T05:59:59"; //"UU/XX/2018 05:59:59" para finalizar a las 23:59 horas del día UU
          generate (startDate,endDate);
          
          startDate = "1-08-"+year+"T06:00:00"; //"II/XX/2018 06:00:00" para comenzar a las 00:00 horas del día II
          endDate = "31-08-"+year+"T05:59:59"; //"UU/XX/2018 05:59:59" para finalizar a las 23:59 horas del día UU
          generate (startDate,endDate);
          
          startDate = "1-09-"+year+"T06:00:00"; //"II/XX/2018 06:00:00" para comenzar a las 00:00 horas del día II
          endDate = "30-09-"+year+"T05:59:59"; //"UU/XX/2018 05:59:59" para finalizar a las 23:59 horas del día UU
          generate (startDate,endDate);
          
          startDate = "1-10-"+year+"T06:00:00"; //"II/XX/2018 06:00:00" para comenzar a las 00:00 horas del día II
          endDate = "31-10-"+year+"T05:59:59"; //"UU/XX/2018 05:59:59" para finalizar a las 23:59 horas del día UU
          generate (startDate,endDate);
          
          startDate = "1-11-"+year+"T06:00:00"; //"II/XX/2018 06:00:00" para comenzar a las 00:00 horas del día II
          endDate = "30-11-"+year+"T05:59:59"; //"UU/XX/2018 05:59:59" para finalizar a las 23:59 horas del día UU
          generate (startDate,endDate);
          
          startDate = "1-12-"+year+"T06:00:00"; //"II/XX/2018 06:00:00" para comenzar a las 00:00 horas del día II
          endDate = "31-12-"+year+"T05:59:59"; //"UU/XX/2018 05:59:59" para finalizar a las 23:59 horas del día UU
          generate (startDate,endDate);
     }
    
    public synchronized static void generate (String startDate, String endDate ) throws ParseException, Exception {
        String tittle = ""; String edate = ""; String stdate = ""; String va = ""; String val = "";
        List<String> dataexp = new ArrayList<>();
        FacebookPageUtils fb = new FacebookPageUtils(userPage, API_VERSION, ACCESS_TOKEN);
        PagePostData pageposts = new PagePostData(API_VERSION, ACCESS_TOKEN, urlPage, startDate, endDate);
        List<String> metric = new ArrayList<>();
        
        metric.add("post_impressions_unique");
        
        /*
        
        metric.add("post_impressions_fan_unique");
        metric.add("post_impressions_organic_unique");
        metric.add("post_impressions_viral_unique");
        metric.add("post_impressions_nonviral_unique");
        metric.add("post_impressions_by_story_type_unique");
        
        metric.add("post_engaged_users");
        
        metric.add("post_negative_feedback_unique");
        metric.add("post_negative_feedback_by_type_unique");
        metric.add("post_clicks_unique");
        metric.add("post_clicks_by_type_unique");*/
        
        metric.add("post_reactions_like_total");
        metric.add("post_reactions_love_total");
        metric.add("post_reactions_wow_total");
        metric.add("post_reactions_haha_total");
        metric.add("post_reactions_sorry_total");
        metric.add("post_reactions_anger_total");
        
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

