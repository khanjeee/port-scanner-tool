package com.mycompany.demowelcomelambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Iqrar
 */
public class SocketResponse implements RequestHandler<LinkedHashMap, Object> {
	public static List<HashMap<String, String>> data = new ArrayList<>();
	public static String[] multipleports;
	public static String ip, ports;
	int portint;
	 String invalidports = "valid ports";
	ResponseJsonclass obj;
	InvalidClass obj2;

	@Override
	public Object handleRequest(LinkedHashMap i, Context cntxt) {
		try {

			obj = new ResponseJsonclass();
			obj.body.clear();
			obj.header.clear();
			// ip =
			// String.valueOf(((LinkedHashMap)((LinkedHashMap)i.get("params")).get("querystring")).get("ip_address"));
			ports = String.valueOf(((LinkedHashMap) ((LinkedHashMap) i.get("params")).get("querystring")).get("rules"));
			ip = String.valueOf(i.get("ip_address"));

			PortSatus conn = new PortSatus();
			obj.header.put("response_code", "200");
			obj.header.put("message", "Success");
			multipleports = ports.split(",");
			
			for (int port = 0; port < multipleports.length; port++) {
				if (multipleports[port].length() <= 5) {
					try {
						portint = Integer.parseInt(multipleports[port]);
					} catch (NumberFormatException e) {
						// cntxt.getLogger().log("Invalid Ports");
						invalidports = "invalid ports";
					}
				} else {
					invalidports = "invalid ports";
				}
			}
			if (invalidports.equals("invalid ports")) {
				// header.clear();
				obj2 = new InvalidClass();
				obj2.header.clear();
				obj2.header.put("response_code", "500");
				obj2.header.put("message", "invalid ports!");
			} else {
				for (int port = 0; port < multipleports.length; port++) {
					HashMap<String, String> values = new HashMap<String, String>();
					obj.body.add(conn.connect(ip, Integer.parseInt(multipleports[port])));
				}
			}
		} catch (SecurityException ex) {
			Logger.getLogger(SocketResponse.class.getName()).log(Level.SEVERE, null, ex);
		}
		if (invalidports.equals("invalid ports")) {
			return obj2;
		} else {
			return obj;
		}
	}
}

class ResponseJsonclass {
	public HashMap<String, String> header = new HashMap<String, String>();
	public ArrayList<HashMap<String, String>> body = new ArrayList<HashMap<String, String>>();
};

class InvalidClass {
	public HashMap<String, String> header = new HashMap<String, String>();
	public HashMap<String, String> body = new HashMap<String, String>();
}
