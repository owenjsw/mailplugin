package com.git.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.git.bean.User;
import net.sf.json.JSONObject;

import com.git.bean.Cache;
import com.git.dao.ValidateUserDao;
import com.git.queue.ThreadPoolManager;
import com.git.util.CacheManager;
import com.git.util.MD5Util;

public class GetServlet extends HttpServlet{
	private ExecutorService pool = ThreadPoolManager.getThreadPoolManager();
	private User user = new User();
	private Cache cache = new Cache();
	private Set<User> users = new HashSet<User>();
	private String mail;
	private String username;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
//		String msg = JsonParse.readJSONString(req);
//		JSONObject jsonObject = JSONObject.fromObject(msg);
		
//		 mail = jsonObject.getString("mail");
//		username = jsonObject.getString("user");
		 mail = req.getParameter("mail");
			username = req.getParameter("user");
		System.out.println("````````````````````````````````````````````````````````````````");
		System.out.println("mail ="+mail);
		System.out.println("user="+user);
		System.out.println("````````````````````````````````````````````````````````````````");
		pool.execute(new Runnable(){
			public void run() {
				if(CacheManager.hasCache("users"))
				users = (Set<User>) CacheManager.getCache("users").getValue();
				user.setEmail(mail);
				user.setUser(username);
				user.setActiveCode(MD5Util.string2MD5(mail));
				user.setDateUpdate(new Timestamp(System.currentTimeMillis()));
				users.add(user);
				cache.setKey("users");
				cache.setValue(users);
				CacheManager.updateCache("users", cache);
				ValidateUserDao.sendHtml(mail);
			}
		});
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,resp);
	}
	
	
}
