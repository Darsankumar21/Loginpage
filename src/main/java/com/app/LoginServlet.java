package com.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//create table user(userid varchar(20),password varchar(20));
// insert into user values('darsan','123456');
// select * from user where userid = 'darsan' and password = '123456';
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/srmdb","root","123456");
			Statement st  = conn.createStatement();
			String query ="select * from user where userid = '"+userId+"' and password = '"+password+"'";
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				out.print("<h1>"+userId+":Welcome to Homepage</h1><br>");
				out.print("<h1>Login Successzfully</h1>");
			}else {
				out.print("<h1>"+userId+":please enter correct userid and Password</h1><br>");
				out.print("<h1>Login Failed</h1>");
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			out.print("<h1>Login Failed!! beacuse of server exception</h1>");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.print("<h1>Login Failed!! beacuse of server exception</h1>");
			e.printStackTrace();
		}
//		out.print("UserId:"+userId);
//		out.print("password:"+password);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
