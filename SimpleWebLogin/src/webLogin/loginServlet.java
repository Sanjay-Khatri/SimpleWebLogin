package webLogin;

import java.io.File;
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
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username =request.getParameter("username");
		String password=request.getParameter("password");
		
		
		if(!username.isEmpty() && !password.isEmpty()) {
			String url = "jdbc:sqlite:D:/student.db";			
			try {
				Class.forName("org.sqlite.JDBC");
				Connection conn = DriverManager.getConnection(url);			
//				System.out.println("CONNECTed");			
				Statement mystmt = conn.createStatement();			
				String stm ="select username, password from student_info where username = '"+ username +"' and password = '"+ password +"';";				
				ResultSet result = mystmt.executeQuery(stm);	
//				System.out.println(stm);
				if (result.next()) {
					HttpSession session = request.getSession();
					session.setAttribute("name", username);
					response.sendRedirect("index.jsp");					
				}else {
					response.sendRedirect("loginError.jsp");
				}
				conn.close();			
//				System.out.println("SUCCESS");
				
			}catch (ClassNotFoundException e) {
				PrintWriter out = response.getWriter();
				out.println(e);
			}catch (SQLException e) {
				PrintWriter out = response.getWriter();
				out.println(e);
				
			}
		}	else {
			response.sendRedirect("loginError.jsp");
		}
	}
}
