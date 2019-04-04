package webLogin;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class registrationservlet
 */
@WebServlet("/registrationservlet")
public class registrationservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registrationservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username =request.getParameter("username");
		String password=request.getParameter("password");
		String c_password=request.getParameter("confirm_password");
		
//		System.out.println(username);
//		System.out.println(password);
//		System.out.println(c_password);
		
		
		if(! username.isEmpty() && password.equals(c_password)) {
//			System.out.println("IN THE REGISTRATION");
			String url = "jdbc:sqlite:D:/student.db";
			try {
				Class.forName("org.sqlite.JDBC");
				Connection conn = DriverManager.getConnection(url);			
//				System.out.println("CONNECTed");			
				Statement mystmt = conn.createStatement();
				String createdatabase = "create table if not exists student_info(username PRIMARYKEY NOT NULL UNIQUE , password NOT NULL);";
				mystmt.execute(createdatabase);
				String stm ="insert into student_info (username, password) values ('"+ username +"', '"+ password +"');";				
				mystmt.execute(stm);	
//				System.out.println(stm);									
				response.sendRedirect("login.jsp");
				conn.close();
			} catch (ClassNotFoundException e) {
				PrintWriter out = response.getWriter();
				out.println(e);
			}catch (SQLException e) {
				PrintWriter out = response.getWriter();
				out.println(e);				
			}
		}else {
			response.sendRedirect("registrationError.jsp");
		}
	}

}
