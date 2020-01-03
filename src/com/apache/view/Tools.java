package com.apache.view;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class Tools {
	public static void startApp(String pro) {
		try {
			Runtime.getRuntime().exec(pro);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void paint(String message) throws AWTException {
		Clipboard clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection selection=new StringSelection(message);
		clipboard.setContents(selection, null);
		Robot robot=new Robot();
		robot.delay(1000);
		for(int i=1;i<=15;i++) {
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(500);
		}
		
		
	}
	public static void mouse(int type) throws AWTException {
		Robot robot=new Robot();
		Random random=new Random();
		
			while(true) {
				if(type==1) {
					robot.mouseMove(random.nextInt(900), random.nextInt(900));
				}else if(type==2){
					robot.mouseMove(random.nextInt(), random.nextInt());
					
				}else {
					robot.mouseMove(500, 500);
				}
				
			}
		
		
	}
	
	public static String getJDK() {
		String path=null;
		boolean flag=false;
		Map<String , String> map=System.getenv();
		if(map.containsKey("JAVA_HOME")||map.containsKey("java_home")) {
			path=map.get("JAVA_HOME");
			flag= isJDK(path);
			if(flag==true) {
				return path;
			}else {
				String[] pathList=map.get("Path").split(";");
				for(String jdk:pathList) {
					if((jdk.contains("java")||jdk.contains("jdk"))&&isJDK(jdk)) {
						return jdk;
					}
				}
			}
		}
		return null;
	}
	public static boolean isJDK(String path) {
		File file=new File(path+"/bin/java.exe");
		return file.exists();
	}
	
	public static void copyFile(String srcFileAddress,String targetFolderAddress) throws Exception {
		File srcFile = new File(srcFileAddress);
		File tarFoder = new File(targetFolderAddress,srcFile.getName());
		BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(srcFile));
		BufferedOutputStream  bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(tarFoder));
		
		byte[] bys = new byte[1024];
		int len = 0;
		while ((len = bufferedInputStream.read(bys)) != -1) {
			bufferedOutputStream.write(bys, 0, len);
		}
		
		bufferedInputStream.close();
		bufferedOutputStream.close();
	}
	
	public static void read(String message) {
		ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");
		Dispatch sapo = sap.getObject();
		try {

			sap.setProperty("Volume", new Variant(100));
			sap.setProperty("Rate", new Variant(1));

			Variant defalutVoice = sap.getProperty("Voice");

			Dispatch dispdefaultVoice = defalutVoice.toDispatch();
			Variant allVoices = Dispatch.call(sapo, "GetVoices");
			Dispatch dispVoices = allVoices.toDispatch();
			Dispatch setvoice = Dispatch.call(dispVoices, "Item", new Variant(1)).toDispatch();
			ActiveXComponent voiceActivex = new ActiveXComponent(dispdefaultVoice);
			ActiveXComponent setvoiceActivex = new ActiveXComponent(setvoice);
			Variant item = Dispatch.call(setvoiceActivex, "GetDescription");
			Dispatch.call(sapo, "Speak", new Variant(message));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sapo.safeRelease();
			sap.safeRelease();
		}
	}

}
