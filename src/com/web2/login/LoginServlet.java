package com.web2.login;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web2.domain.User;
import com.web2.service.UserService;

public class LoginServlet extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		
		//得到上下文
		ServletContext context = this.getServletContext();
		//设置全局参数
		context.setAttribute("count", 0);
		System.out.println("参数已经初始化");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//得到访问的参数
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		
		//将参数封装成User对象
		User user = null;
		try {
			user = UserService.getUserByUserNameAndPassword(userName, password);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		//判断user是否为空
		resp.setContentType("text/html;charset=utf-8");
		if(user == null) {
			
			resp.getWriter().print("没有此用户，3s后返回登录页面");
			resp.setHeader("refresh", "3;url=/web2/login.html");
		} else {
			resp.getWriter().print("欢迎回来："+req.getParameter("username"));
			
			//获取上下文 
			ServletContext context = this.getServletContext();
			Integer cnt = (Integer)context.getAttribute("count");
			context.setAttribute("count", ++cnt);
			System.out.println("次数增加成功");
			
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		this.doGet(req, resp);
	}

}
