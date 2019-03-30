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

package edu.artec.apps.facebook.net;
import java.io.IOException;

/**
 *
 * @author imstu
 */
public class OpenURL {
    
    
    public static void run(String url) throws IOException {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win"))
            windows(url);
        if (os.contains("mac"))
            mac(url);
        if (os.contains("nix") || os.contains("nux"))
            linux(url);
    }
    
    
    public static void mac(String url) throws IOException {
        Runtime rt = Runtime.getRuntime();
        rt.exec("open " + url);
    }
    
    public static void linux(String url) throws IOException {
        Runtime rt = Runtime.getRuntime();
        String[] browsers = { "epiphany", "firefox", "mozilla", "konqueror",
                                 "netscape", "opera", "links", "lynx" };
        StringBuffer cmd = new StringBuffer();
        for (int i = 0; i < browsers.length; i++)
            if(i == 0)
                cmd.append(String.format("%s \"%s\"", browsers[i], url));
            else
                cmd.append(String.format(" || %s \"%s\"", browsers[i], url));     
        rt.exec(new String[] { "sh", "-c", cmd.toString() });
    }
    
    public static void windows(String url) throws IOException {
        Runtime rt = Runtime.getRuntime();
        rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
    }
    
}