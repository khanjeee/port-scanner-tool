/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.demowelcomelambda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shoaib
 */
public class PortSatus {
    Socket pingSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    String readings = "";
    public HashMap connect(String serverName,int port) {
        readings = "";
        HashMap<String, String> values = new HashMap<String, String>();
        values.clear();
        try {
            pingSocket = new Socket();
            pingSocket.connect(new InetSocketAddress(serverName, port), 200);
            out = new PrintWriter(pingSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
            out.println("ping");
            readings = in.readLine().toString();
            if (readings.length() > 0){
                readings = "Opened";
                values.put("port", port+"");
                values.put("status", "open");
            }
            out.close();
            in.close();
            pingSocket.close();
        } catch (IOException e) {
            readings = "Closed";
            values.put("port", port+"");
            values.put("status", "close");
        }catch (NullPointerException e) {
              try {
                out.close();
                in.close();
                values.put("port", port+"");
                values.put("status", "close");
            } catch (IOException ex) {
                Logger.getLogger(PortSatus.class.getName()).log(Level.SEVERE, null, ex);
            }
            readings = "Closed";
        } 
       return values;
    }
    
}
