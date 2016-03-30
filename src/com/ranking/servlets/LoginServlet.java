package com.ranking.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ranking.dao.AuthenticationDAO;

public class LoginServlet extends HttpServlet {
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 PrintWriter pw=response.getWriter();
			 response.setContentType("text/html");
			 String userName=request.getParameter("userName");
			 String password=request.getParameter("password");
			 String result=AuthenticationDAO.doAuthenticate(userName, password,request);
			 if(result=="valid"){
				 ServletContext context=getServletContext();
				 RequestDispatcher dispatcher=context.getRequestDispatcher("/rank.jsp");
				 dispatcher.forward(request, response);
			 }else{
				 pw.println("<h2><b><i><font color='#FF0000'>"+"username or password are invalid"+"</b></i></font>");
			 }

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
