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
 * Servlet implementation class adddata
 */
@WebServlet("/adddata")
public class adddata extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("bookname");
		double price = Double.parseDouble(request.getParameter("bookprice"));
		String author = request.getParameter("author");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "Avi@7700");
			String sql = "insert into booktable(name,price,author) values(?,?,?)";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setDouble(2, price);
			ps.setString(3, author);

			int status = ps.executeUpdate();
			PrintWriter pw = response.getWriter();
			if (status == 1) {
				pw.print("<h1>" + status + " Record Inserted");
			}
			else
			pw.print("<h1>" + " Record Not Inserted");
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			

	}

}
