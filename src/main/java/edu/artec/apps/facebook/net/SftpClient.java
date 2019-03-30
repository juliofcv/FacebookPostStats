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


import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.util.Properties;

public class SftpClient {
    
    private final String server;
    private final int port;
    private final String login;
    private final String password;
    private final String workdir;
    
    private JSch jsch = null;
    private Session session = null;
    private Channel channel = null;
    private ChannelSftp c = null;
    
    public SftpClient(String server, int port, String login, String password, String workdir) {
        this.server = server;
        this.port = port;
        this.login = login;
        this.password = password;
        this.workdir = workdir;
    }

    public void connect() throws JSchException {
        jsch = new JSch();
        session = jsch.getSession(login, server, port);
        session.setPassword(password);
        Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();
        channel = session.openChannel("sftp");
        channel.connect();
        c = (ChannelSftp) channel;
    }

    public void uploadFile(String sourceFile, String destinationFile) throws Exception {
        if (c == null || session == null || !session.isConnected() || !c.isConnected())
            throw new Exception("Connection to server is closed. Open it first.");
        c.cd(workdir);
        c.put(sourceFile, destinationFile);
    }

    public void retrieveFile(String sourceFile, String destinationFile) throws Exception {
        if (c == null || session == null || !session.isConnected() || !c.isConnected())
            throw new Exception("Connection to server is closed. Open it first.");
        c.get(sourceFile, destinationFile);
    }
    
    public void disconnect() {
        if (c != null)
            c.disconnect();
        if (channel != null)            
            channel.disconnect();
        if (session != null)            
            session.disconnect();
    }
       
}