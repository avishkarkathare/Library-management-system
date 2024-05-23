package servelt_pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class displaybook
 */
@WebServlet("/displaybook")
public class displaybook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
	
	 
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "Avi@7700");

			String sql = "select * from booktable";

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
 				pw.print("Book Id is : " + rs.getInt("bookId") );
				pw.print("Book Name is : "  + rs.getString("bookName"));
				pw.print("Book Price is : " + rs.getDouble("bookPrice"));
				pw.print("Book Author is : " +  rs.getString("author"));
				pw.print("*************************************************");
 			}
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
