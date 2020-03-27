

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		Connection con;
		Statement stmt;
		String usertel = request.getParameter("UserTel").trim();
		String userpwd = request.getParameter("UserPwd").trim();
		String url = "jdbc:mysql://localhost/finaltask";
		
		try {
			con = (Connection) DriverManager.getConnection(url,"root","root");
			String sql = "select * from user where UserTel = '"+usertel+"' and UserPwd = '"+userpwd+"'";
			stmt = (Statement) con.prepareStatement(sql);
			ResultSet rSet = (ResultSet) stmt.executeQuery(sql);
			
			if(rSet.next()) {
				out.println("login successfully!");
			}
			else {
				out.println("can not login!");
			}
			con.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
