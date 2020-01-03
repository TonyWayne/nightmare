package com.apache.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

public class FileSys {
	
	public static void getAllFile(File mFile,List<String> list) {
		File[] files = mFile.listFiles();
		if (files != null) {
			
			for (File file : files) {
				if (file.isDirectory()) {
				} else {
					String fileName = file.getName();
					if (fileName.endsWith(".jpg") || fileName.endsWith(".png")) {
						list.add(file.getPath());
					}
				}
			}
		}
	}
	
	public static String getHard() {
		File[] roots=File.listRoots();
		String hardInfo="";
		for(File file:roots) {
			String name=file.getAbsolutePath();
			String total=name+"总容量"+file.getTotalSpace()/(1024*1024)/1024+"G";
			String free=name+"可用"+file.getFreeSpace()/(1024*1024)/1024+"G";
			String used=name+"已用"+((file.getTotalSpace()-file.getFreeSpace())/(1024*1024)/1024)+"G";
			hardInfo=hardInfo+total+","+free+","+used;
		}
		return hardInfo;
	}

}
