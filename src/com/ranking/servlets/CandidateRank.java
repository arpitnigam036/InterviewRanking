package com.ranking.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ranking.dao.AuthenticationDAO;

public class CandidateRank extends HttpServlet {
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 PrintWriter pw=response.getWriter();
			 response.setContentType("text/html");
			 
				 int candidateId=Integer.parseInt(request.getParameter("candidateId"));
				 int rank=Integer.parseInt(request.getParameter("rank"));
				 AuthenticationDAO.saveRank(candidateId,rank, request);
				 request.setAttribute("msg","Rank Sucessfully Saved");
				 RequestDispatcher rs=request.getRequestDispatcher("rank.jsp");
				 rs.forward(request, response); 
			 
				 //request.setAttribute("msg","Enter Value");
				 //RequestDispatcher rs=request.getRequestDispatcher("rank.jsp");
				 //rs.forward(request, response);
			 
			 
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
