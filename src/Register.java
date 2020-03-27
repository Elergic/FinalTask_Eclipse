

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
    	// TODO Auto-generated method stub
    	super.init(config);
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		Connection con;
		PreparedStatement pst;
		Statement stmt;
		String usertel = request.getParameter("UserTel").trim();
		String userpwd = request.getParameter("UserPwd").trim();
		String useremail = request.getParameter("UserEmail").trim();
		String username = request.getParameter("UserName").trim();
		String userid = request.getParameter("UserId").trim();
		String userschool = request.getParameter("UserSchool").trim();
		String usercharacter = request.getParameter("UserCharacter").trim();	
		String url = "jdbc:mysql://localhost/finaltask";
		
		try {
			con = (Connection) DriverManager.getConnection(url,"root","root");
			String sql = "insert into user values(?,?,?,?,?,?,?)";
			pst = (PreparedStatement) con.prepareStatement(sql);
			pst.setString(1,usertel);
			pst.setString(2,userpwd);
			pst.setString(3,useremail);
			pst.setString(4,username);
			pst.setString(5,userid);
			pst.setString(6,userschool);
			pst.setString(7,usercharacter);
			pst.executeUpdate();
			con.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		try {
			con = (Connection) DriverManager.getConnection(url,"root","root");
			String sql_1 = "select * from User where UserTel = '"+ usertel +"' and UserPwd = '"+ userpwd +"'";
			stmt = (Statement) con.prepareStatement(sql_1);
			ResultSet rSet = (ResultSet) stmt.executeQuery(sql_1);
			if(rSet.next()) {
				out.println("register successfully!");
			}
			else {
				out.println("can not register!");
			}
			con.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
