package com.apache.view;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class BGM {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        AePlayWave apw=new AePlayWave("resources/bgm.wav");
        apw.start();
    }

}

class AePlayWave extends Thread {

    private String filename;
    public AePlayWave(String wavfile) {
        filename = wavfile;

    }

    public void run() {

        File soundFile = new File(filename);

        // 获取音频输入�?
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e1) {
            e1.printStackTrace();
            return;
        }
        // 获取音频编码对象
        AudioFormat format = audioInputStream.getFormat();
        // 设置数据输入
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        try {
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        auline.start();
      /*
       * 从输入流中读取数据发送到混音�?
       */
        int nBytesRead = 0;
        byte[] abData = new byte[512];

        try {
            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0)
                    auline.write(abData, 0, nBytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } finally {
            // 清空数据缓冲,并关闭输�?
            auline.drain();
            auline.close();
        }
    }
}
