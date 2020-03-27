

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class Event
 */
@WebServlet("/Event")
public class Event extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Event() {
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
		String url = "jdbc:mysql://localhost/finaltask";
		String temp = "";
		
		try {
			con = (Connection) DriverManager.getConnection(url,"root","root");
			String sql = "select EventTitle from event";
			stmt = (Statement) con.prepareStatement(sql);
			ResultSet rSet = (ResultSet) stmt.executeQuery(sql);
			
			rSet.last();
			int rowCount = rSet.getRow();
			rSet.first();
			
			for(int i = 0;i < rowCount;i++) {
				
				if(i == rowCount - 1) {
					temp = temp + rSet.getString("EventTitle");
				}
				else {
					temp = temp + rSet.getString("EventTitle") + "|";
				}
				rSet.next();
			}
			
			out.println(temp);
			
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
		
	}

}
