import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UserDAO; // Import UserDAO

@WebServlet("/LoginServlet") // Ensure this matches the action in your form
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");

	    UserDAO userDAO = new UserDAO();
	    
	    try {
	        if (userDAO.validateUser(email, password)) {
	            response.sendRedirect("login_sus.html"); // Redirect on success
	        } else {
	            response.sendRedirect("error.html?error=InvalidCredentials"); // Redirect back with an error
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect("error.html"); // Redirect to a custom error page
	    }
	}

}
