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

import com.jcraft.jsch.JSchException;
import java.io.File;

/**
 *
 * @author imstu
 */
public class PublishReport {
    
    public static void publishInHost(String file, String server, int port, String login, String password, String workdir) throws JSchException, Exception {
        File upfile = new File(file);
        SftpClient client = new SftpClient(server, port,login,password,workdir);
        client.connect();
        client.uploadFile(upfile.getPath(), upfile.getName());
        client.disconnect();
        System.out.println("Report generated in:");
        String urlReport = "http://"+workdir+"/"+upfile.getName();
        System.out.println(urlReport);
        OpenURL.run(urlReport);
    }
    
}
