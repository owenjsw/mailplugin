package com.git.util;

import java.util.ArrayList;
import java.util.List;

import com.git.bean.SMTPProp;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class PostMessageToCenter {
	public void connectCenter(String user , int code){
		SMTPProp prop = new SMTPProp();
	
		prop = (SMTPProp) CacheManager.getCache("prop").getValue();
		String path = prop.getCenter();
		try {
			HttpPost url = new HttpPost(path+"/"+code);
			List<NameValuePair> values = new ArrayList<NameValuePair>();
			values.add(new BasicNameValuePair("userId",user));
			url.setEntity(new UrlEncodedFormEntity(values,HTTP.UTF_8));
			
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse httpResponse = httpClient.execute(url);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
