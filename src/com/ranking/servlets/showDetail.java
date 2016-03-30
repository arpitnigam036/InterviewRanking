package com.ranking.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ranking.dao.AuthenticationDAO;

public class showDetail extends HttpServlet {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String interviewer=null;
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter pw=response.getWriter();
			response.setContentType("text/html");
			try
			{
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
				int interviverId=(Integer) request.getSession().getAttribute("id");
				if(interviverId==1){
					interviewer="interviewer1";
				}else if(interviverId==2){
					interviewer="interviewer2";
				}else{
					interviewer="interviewer3";
				}
				ps=conn.prepareStatement("select student_id,student_name,"+interviewer+" from studentDetail");
				rs=ps.executeQuery();
				pw.println("<table>");
				pw.println("<tr>");
				pw.println("<th>"+"ID"+"</th>");
				pw.println("<th>"+"Name"+"</th>");
				pw.println("<th>"+"Rank"+"</th>");
				pw.println("</tr>");
				while(rs.next()){
						pw.println("<tr>");
						pw.println("<th>"+rs.getString(1)+"</th>");
						pw.println("<th>"+rs.getString(2)+"</th>");
						pw.println("<th>"+rs.getString(3)+"</th>");
						pw.println("</tr>");
					}
					pw.println("</table>");
					pw.println("<br>");
					pw.println("<br>");
					pw.println("<br>");
					pw.println("<a href="+request.getContextPath()+"/rank.jsp>HOME</a>");
					rs.close();
			}//try
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
			finally
			{
				try{
					if(rs!=null)
						rs.close();
				}
				catch(Exception e)
				{ e.printStackTrace(); }


				try{
					if(ps!=null)
						ps.close();
				}
				catch(Exception e)
				{ e.printStackTrace(); }


				try{
					if(conn!=null)
						conn.close();
				}
				catch(Exception e)
				{ e.printStackTrace(); }


			}//finally
		
				
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		}

}
