package com.git.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.git.bean.User;
import com.git.util.CacheManager;
import com.git.util.JsonParse;
import com.git.util.PostMessageToCenter;

public class ValidateServlet extends HttpServlet{
	private Set<User> users = new HashSet<User>();
	private PostMessageToCenter  post = new PostMessageToCenter();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			String msg = JsonParse.readJSONString(req);
			JSONObject jsonObject = JSONObject.fromObject(msg);
			final String codeActive = jsonObject.getString("activeCode");
			String username = null;
			if(CacheManager.hasCache("users")){
				users = (Set<User>) CacheManager.getCache("users").getValue();
				for(User user:users){
					if(user.getActiveCode().equals(codeActive)){
						//success
						username = user.getUser();
						break;
					}
				}
			}
			if (username != null){
				//success page
				post.connectCenter(username, 1);
			}else{
				//error page
			}
	}
	
}
