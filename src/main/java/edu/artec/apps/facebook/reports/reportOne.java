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

package edu.artec.apps.facebook.reports;

import edu.artec.apps.facebook.SFTPConf;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 *
 * @author Julio Chinchilla
 */
public class reportOne {
    

    
    private final static String head = 
            "<html lang=\"es\">\n" +
"  <head>\n" +
"    <title>Estadísticas Facebook</title>\n" +
"  \n" +
"  <meta name=\"description\" content=\"ADS GUATE\">\n" +
"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\">\n" +
"  <meta name=\"geo.region\" content=\"GT\" />\n" +
"  <meta name=\"geo.placename\" content=\"Guatemala\" />\n" +
"  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" >\n" +
"  <meta name=\"googlebot\" content=\"index,notranslate,noimageindex,follow\">\n" +
"  <meta name=\"robots\" content=\"index,notranslate,noimageindex,follow\">\n" +
"  <meta name=\"google\" content=\"notranslate\" />\n" +
"  <meta name=\"keywords\" content=\"adsguate, ads guate, ads Guatemala\" />\n" +
"  <meta property=\"og:title\" content=\"ADS GUATE\" />\n" +
"  <meta property=\"og:type\" content=\"article\" />\n" +
"  <meta property=\"og:url\" content=\"https://www.imstudio.tv\" />\n" +
"  <meta property=\"og:image\" content=\"https://www.imstudio.tv/apps/img/preview.png\" />\n" +
"\n" +
"  <meta property=\"og:description\" content=\"Estadísticas avanzadas\" />\n" +
"  <meta property=\"og:locale:alternate\" content=\"es_LA\" />\n" +
"\n" +
"  <meta property=\"fb:app_id\" content=\"2152396761652119\" />\n" +
"  <meta property=\"article:author\" content=\"https://www.facebook.com/adsguate\" />\n" +
"\n" +
"  <meta property=\"fb:pages\" content=\"507196352782415\" />\n" +
"  \n" +
"  <meta name=\"twitter:card\" content=\"summary_large_image\">\n" +
"  <meta name=\"twitter:site\" content=\"@adsguate_\">\n" +
"  <meta name=\"twitter:creator\" content=\"@iadsguate_\">\n" +
"  <meta name=\"twitter:title\" content=\"ADS GUATE\">\n" +
"  <meta name=\"twitter:description\" content=\"Estadísticas avanzadas\">\n" +
"  <meta name=\"twitter:image\" content=\"https://www.imstudio.tv/apps/img/preview.png\">  \n" +
"\n" +
"  <link rel=\"icon\" type=\"imge/x-icon\" href=\"https://www.imstudio.tv/apps/img/arrow.ico\" />" +

            "    <title>FBStats Report</title>"+
            "    <meta charset=\"utf-8\">"+
            "    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\n" +
            "    <script type=\"text/javascript\">\n" +
            "      google.charts.load('current', {'packages':['corechart']});\n" +
            "      google.charts.setOnLoadCallback(drawChart);\n" +
            "\n" +
            "      function drawChart() {\n" +
            "        var data = google.visualization.arrayToDataTable([\n\t\t\t\t\t['Fecha'," ;

    
    private final static String foot0 = 
            "]);\n" +
            "\n" +
            "        var options = {\n" +
            "          title: '";
    private final static String foot1 =
            "',\n" +
            "          hAxis: {title: 'Facebook Post Stats - Dev by Julio Chinchilla',  titleTextStyle: {color: '#333', fontSize: '12'}},\n" +
            "          vAxis: {minValue: 0},\n" +
            "backgroundColor: '#FEFEFA',\n" +
            "colors: ['#5f249f', '#ff6900', '#ffde00', '#00bce1', '#d62598', '#0762c8', '#79c300', '#9b26b6', '#ffa400', '#1e6f30', '#e277cd', '#d6e865', '#ff9da2', '#ee3831', '#00b2a2', '#b86125'],\n" +
            "animation: {\n" +
            "          duration: 500,\n" +
            "          easing: 'out',\n" +
            "          startup: true\n" +
            "      }\n" +
      
            "        };\n" +
            "\n" +
            "        var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));\n" +
            "function selectHandler() {\n" +
"          var selectedItem = chart.getSelection()[0];\n" +
"          if (selectedItem) {\n" +
"            var topping = data.getValue(selectedItem.row," ;
    private final static String foot1b = ");\n" +
"             window.open(topping,'_blank');\n" +
"          }\n" +
"        }\n" +
"\n" +
"        google.visualization.events.addListener(chart, 'select', selectHandler);"+
            "        chart.draw(data, options);\n" +
            "      }\n" +
            "    </script>\n" +
            " <style type=\"text/css\">\n" +
"    #chart_wrap {\n" +
"    position: relative;\n" +
"    padding-bottom: 100%;\n" +
"    height: 0;\n" +
"    overflow:hidden;\n" +
"}\n" +
"\n" +
"#chart_div {\n" +
"    position: absolute;\n" +
"    top: 0;\n" +
"    left: 0;\n" +
"    width:100%;\n" +
"    height:800px;\n" +
"}\n" +
"    </style>"+
            "  </head>\n" +
            "  <body>\n" +
                "<div id=\"chart_wrap\">\n" +
                    "<div id=\"chart_div\"></div>\n" +
                "</div>"+
            "  </body>\n" +
            "</html>";
    
    public synchronized static void generate(List<String> data, String tittle, String file, String hRef, boolean publish) throws FileNotFoundException, UnsupportedEncodingException, Exception {
        StringBuilder d = new StringBuilder();
        for(String dd:data)
            d.append(dd);
        String report = head + d.toString() + foot0 + tittle + foot1 + hRef + foot1b;
        PrintWriter writer = new PrintWriter(file, "UTF-8");
        writer.write(report);
        writer.close();
        if (publish)
            edu.artec.apps.facebook.net.PublishReport.publishInHost(file, SFTPConf.server, SFTPConf.port, SFTPConf.login, SFTPConf.password, SFTPConf.workdir);
        
    }
    
    
}