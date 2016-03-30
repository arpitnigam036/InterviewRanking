package com.ranking.dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthenticationDAO{
	
	private static final String qryAuthenticate="select count(*) from userLogin where userName=? and password=?";
	private static final String qryUserId="select userId from userLogin where userName=? and password=?";
	
	@SuppressWarnings("resource")
	public static String doAuthenticate(String userName, String password,HttpServletRequest request) {
		
			Connection conn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			try
			{
				
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost/3306/interviewranking","root","arpit");
				ps=conn.prepareStatement(qryAuthenticate);
				ps.setString(1,userName);
				ps.setString(2,password);
				rs=ps.executeQuery();
				int cont=0;
				if(rs.next());
				cont=rs.getInt(1);
				if(cont==0){
				return "invalid";
				}
				else{
					ps=conn.prepareStatement(qryUserId);
					ps.setString(1, userName);
					ps.setString(2, password);
					rs=ps.executeQuery();
					if(rs.next());
					int userId=rs.getInt(1);
					HttpSession session=request.getSession();
					session.setAttribute("id", userId);
					return "valid";
				}
			}//try
			catch(Exception e)
			{
				e.printStackTrace();
				return "oops internal problem";
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
@SuppressWarnings("resource")
public static void saveRank(int candidateId,int rank,HttpServletRequest request) {
	
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String interviewer=null;
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost/3306/interviewranking","root","arpit");
			int interviverId=(Integer) request.getSession().getAttribute("id");
			if(interviverId==1){
				interviewer="interviewer1";
			}else if(interviverId==2){
				interviewer="interviewer2";
			}else{
				interviewer="interviewer3";
			}
			
			ps=conn.prepareStatement("update studentDetail set "+interviewer+"="+rank+" where student_id="+candidateId);
			int updateRows=ps.executeUpdate();
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

}





