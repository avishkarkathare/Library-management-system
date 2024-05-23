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
 * Servlet implementation class updatedata
 */
@WebServlet("/updatedata")
public class updatedata extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		int bookId = Integer.parseInt(request.getParameter("bookid"));
		String bookName = request.getParameter("bookname");
		double bookPrice = Double.parseDouble(request.getParameter("bookprice"));
		String author = request.getParameter("author");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "Avi@7700");
			String sql = "UPDATE booktable SET name=?, price=?, author=? WHERE id=?";

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, bookName);
			ps.setDouble(2, bookPrice);
			ps.setString(3, author);
			ps.setInt(4, bookId);
			int status = ps.executeUpdate();
			if (status == 1) {
				pw.println("Book details updated successfully");
			} else {
				pw.println("Unable to update book details");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
