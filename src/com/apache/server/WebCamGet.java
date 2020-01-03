package com.apache.server;

import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.WebcamUtils;
import com.github.sarxos.webcam.util.ImageUtils;

public class WebCamGet {
	public static String getWebCam() {
		Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		String fileName = "C://image//webcam" ;       //保存路径即图片名称（不用加后�?�?
		WebcamUtils.capture(webcam, fileName, ImageUtils.FORMAT_PNG);
		webcam.close();
		return fileName;
	}
	


}
