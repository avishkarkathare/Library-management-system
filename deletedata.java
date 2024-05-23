package servelt_pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deletedata
 */
@WebServlet("/deletedata")
public class deletedata extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int bookid= Integer.parseInt(request.getParameter("bookid"));
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root","Avi@7700");
			
			String sql="delete from booktable where id=?";
			
			 
			PreparedStatement ps=con.prepareStatement(sql);
			
			ps.setInt(1, bookid);
			int count = ps.executeUpdate();
			PrintWriter pw =response.getWriter();
			pw.print("<h1>"+count+" Recourd Deleted");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		

 	}

}
