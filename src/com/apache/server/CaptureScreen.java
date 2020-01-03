package com.apache.server;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date; 
import javax.imageio.ImageIO;

public class CaptureScreen {
 
    public static void captureScreen(String fileName, String folder) throws Exception {
 
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        File screenFile = new File(fileName);    
        if (!screenFile.getParentFile().exists()) {  
            screenFile.getParentFile().mkdirs();  
        } 
        if(!screenFile.exists()&& !screenFile .isDirectory()) {
            screenFile.mkdir();
        }
        
        File f = new File(screenFile, folder);        
        ImageIO.write(image, "png", f);
       
    }
 
    public static void main(String[] args) {
        Date dt=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyyMMddHHmmss");
        String data=sdf.format(dt);
        String rd=sdf1.format(dt);
        try {
            captureScreen("c:\\image","desktop.png");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
}