package com.emall.utils;


import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FtpUtil {
    private static final int DEFAULT_PORT = 22;
    /**
     * 创建服务器连接
     * @param host
     * @param user
     * @param password
     * @return
     * @throws IOException
     */
    private static Session openSession(String host,String user,String password) throws IOException{
        Session session = null;
        try{
            JSch jSch = new JSch();
            session = jSch.getSession(user,host,DEFAULT_PORT);
            noCheckHostKey(session);
            session.setPassword(password);
            session.connect();
            if(!session.isConnected()){
                throw new IOException("We can't connection to[" + host + "]");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return session;
    }

    /**
     * 不检查主机键值
     * @param session
     */
    private static void noCheckHostKey(Session session){
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
    }

    /**
     * 连接shell
     * @param session
     * @return
     */
    private static ChannelShell openChannelShell(Session session) {
        ChannelShell channel = null;
        try {
            channel = (ChannelShell) session.openChannel("shell");
            channel.setEnv("LANG", "en_US.UTF-8");
            channel.setAgentForwarding(false);
            channel.setPtySize(500, 500, 1000, 1000);
            channel.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (channel == null) {
            throw new IllegalArgumentException("The channle init was wrong");
        }
        return channel;
    }

    /**
     * 连接sftp
     * @param session
     * @return
     */
    private static ChannelSftp openChannelSftp(Session session) {
        ChannelSftp channel = null;
        try {
            channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return channel;
    }

    public static void uploadFile(String host, String username, String password, String filepath, InputStream inputStream) throws Exception{
        Session session = null;
        ChannelShell openChannelShell = null;
        ChannelSftp openChanelSftp = null;
        try {
            session = openSession(host, username, password);
            openChannelShell = openChannelShell(session);
            openChannelShell.setInputStream(inputStream);
            openChanelSftp = openChannelSftp(session);
            openChanelSftp.put(inputStream, filepath, ChannelSftp.OVERWRITE);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("文件上传错误");
        }finally {
            session.disconnect();
            openChannelShell.disconnect();
            openChanelSftp.disconnect();
        }
    }
}
