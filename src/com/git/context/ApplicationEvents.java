package com.git.context;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.git.bean.Cache;
import com.git.bean.SMTPProp;
import com.git.bean.User;
import com.git.queue.ThreadPoolManager;
import com.git.util.CacheManager;
import com.git.util.LoadProperties;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class ApplicationEvents implements ServletContextListener{
	private static final Logger logger = Logger.getLogger(ApplicationEvents.class);
	public static void getHtml(){
		Document doc;
		Cache cache = new Cache();
		try {
			doc = Jsoup.connect(LoadProperties.getProperty("emailtemplete")).get();
			cache.setValue(doc);
			cache.setKey("mail");
			cache.setExpired(false);
			cache.setTimeOut(60*10000*10000);
			CacheManager.putCache("mail", cache);

				System.out.println("cache mail =========="+doc.toString());
		} catch (IOException e) {
			logger.error("getHtml File error",e);
		}
	}
	public static void getProp(){
		Cache cache = new Cache();
		SMTPProp prop = new SMTPProp();
		prop.setEmailtemplete(LoadProperties.getProperty("emailtemplete"));
		prop.setHost(LoadProperties.getProperty("host"));
		prop.setPort(LoadProperties.getProperty("port"));
		prop.setPwd(LoadProperties.getProperty("pwd"));
		prop.setScaleFactor(LoadProperties.getProperty("scaleFactor"));
		prop.setUsername(LoadProperties.getProperty("username"));
		prop.setReaddress(LoadProperties.getProperty("readress"));
		prop.setCenter(LoadProperties.getProperty("center"));
		cache.setValue(prop);
		cache.setKey("prop");
		cache.setExpired(false);
		cache.setTimeOut(60*10000*10000);
		CacheManager.putCache(cache.getKey().toString(), cache);
	}
	private static void createUsers(){
		Set<User> users = new HashSet<User>();
		Cache cache = new Cache();
		cache.setKey("users");
		cache.setValue(users);
		CacheManager.putCache(cache.getKey().toString(),cache);
	}

	public void contextDestroyed(ServletContextEvent event) {
		CacheManager.clearAll();
		ThreadPoolManager.shutdown();
		
	}

	public void contextInitialized(ServletContextEvent event) {
		getHtml();
		getProp();
		createUsers();
		logger.info("Somethings has been inited");
		ThreadPoolManager.init();	
		
		
	}
}
