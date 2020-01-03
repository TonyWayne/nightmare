package com.apache.server;

import java.net.InetAddress;
import java.util.Properties;

public class TargetInfo {
	 public static String getIP() throws Exception {
	    	InetAddress inetAddressaddress=InetAddress.getLocalHost();
	    	String ip=inetAddressaddress.getHostAddress().toString();
	        return ip;
	    }
	    
	    public static String getInfo() {
	    	Properties properties=System.getProperties();
	    	String osname=properties.getProperty("os.name");
	    	String osArch =properties.getProperty("os.arch");
	    	String osVersion =properties.getProperty("os.version");
	    	return osname+"系统:"+osArch+"位";
	    }
	    public static String getUser() {
	    	return System.getProperty("user.name");
	    }

}
