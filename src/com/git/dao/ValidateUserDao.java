package com.git.dao;


import java.util.Date;
import java.util.Properties;


import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.git.bean.SMTPProp;
import com.git.util.CacheManager;
import com.git.util.MD5Util;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import com.git.context.ApplicationEvents;


public class ValidateUserDao {
	private static Properties  props= null;
	private static final Logger logger = Logger.getLogger(ValidateUserDao.class);
	private static SMTPProp prop = null;
	private static String adress = null;
	static{
		props = new Properties();
		prop =  new SMTPProp();
		if(CacheManager.hasCache("prop")){
			prop = (SMTPProp) CacheManager.getCache("prop").getValue();
			props.setProperty("mail.transport.protocol", "smtp"); 
	        props.setProperty("mail.smtp.host", prop.getHost()); 
	        props.setProperty("mail.smtp.port", prop.getPort()); 
	        props.setProperty("mail.smtp.auth", "true"); 
	        props.setProperty("mail.debug","true"); 
	        adress = prop.getReaddress();
		}else{
			ApplicationEvents.getProp();
			prop = (SMTPProp) CacheManager.getCache("prop").getValue();
			props.setProperty("mail.transport.protocol", "smtp"); 
	        props.setProperty("mail.smtp.host", prop.getHost()); 
	        props.setProperty("mail.smtp.port", prop.getPort()); 
	        props.setProperty("mail.smtp.auth", "true"); 
	        props.setProperty("mail.debug","true"); 
	        adress = prop.getReaddress();
		}
	}
	static class MyAuthenticator extends Authenticator { 

        private String username = prop.getUsername();

        private String password = prop.getPwd(); 

        public MyAuthenticator() { 
            super(); 
        } 

        public MyAuthenticator(String username, String password) { 
            super(); 
            this.username = username; 
            this.password = password; 
        } 

        @Override 
        protected PasswordAuthentication getPasswordAuthentication() { 

            return new PasswordAuthentication(username, password); 
        } 
    } 
	public void sendMail(final String email) throws MessagingException{	
			sendHtml(email);
	}
	
	
	public static void sendHtml(String ads) {
		
		Session session = Session.getDefaultInstance(props , new MyAuthenticator());
		MimeMessage message = new MimeMessage(session);
		try {
			message.setSubject("Confirm your email address for Ciaoyo" , "utf-8");
			message.setFrom(new InternetAddress("ciaoyo@smartteamglb.com"));
			message.setRecipients(RecipientType.TO, InternetAddress.parse(ads));
			message.setSentDate(new Date());
			message.setContent(getHtml(ads).toString(),"text/html;charset=UTF-8");
			message.saveChanges();
			Transport.send(message);
		} catch (MessagingException e) {
			logger.error("SMTP not  connect", e);
		}
	}
	private static String getHtml(String ads){
		String doc;
		boolean flag;
		flag = CacheManager.hasCache("mail");
		if(flag){
			
			doc = writeHTML(ads);
			return doc;
		}else{
			ApplicationEvents.getHtml();
			doc = writeHTML(ads);
			return doc;
		}
	}
	private static String writeHTML(String ads){
		String str;
		Document dochtml;
		str = CacheManager.getCache("mail").getValue().toString();
		dochtml = Jsoup.parse(str);
		Element d1=dochtml.select("font.username").first();
		d1.text(ads);
		Element d2=dochtml.select("a.userval").first();
		d2.attr("href",adress+"?activeCode="+ MD5Util.string2MD5(ads));
		return dochtml.toString();
	}
}
