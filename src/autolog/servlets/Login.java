package autolog.servlets;

import autolog.DAO.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import autolog.core.User;
import autolog.core.Vehicle;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("user: " + username);
        System.out.println("pass: " + password);
        
        UserDAO uDAO = new UserDAO();
        
        User user = uDAO.isAuthenticated(username, password);
        
        if(user != null)
        {
        	HttpSession session = request.getSession();
        	session.setAttribute("userID", user.getID());
        	//VehicleDAO vDAO = new VehicleDAO();
        	//Vehicle v = vDAO.getVehicle(1,1);
        	//int userID = user.getID();
            //request.setAttribute("userId", userID); // add to request
            //request.getSession().setAttribute("userId", userID); // add to session
            //this.getServletConfig().getServletContext().setAttribute("userID", userID); // add to application context
            //request.getRequestDispatcher("Welcome").forward(request, response);
            RequestDispatcher rs = request.getRequestDispatcher("Welcome");
            rs.forward(request, response);
        }
        else
        {
           out.println("Username or Password incorrect");
           RequestDispatcher rs = request.getRequestDispatcher("Index.html");
           rs.include(request, response);
        }
		
	}*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //System.out.println("user: " + username);
        //System.out.println("pass: " + password);
        
    	HttpSession session = request.getSession();
        
        UserDAO uDAO = new UserDAO();
        
        User user = uDAO.isAuthenticated(username, password);
    	ArrayList<Vehicle> v = null;
        
        if(user != null)
        {
        	session.setAttribute("name", user.getName());
        	session.setAttribute("user", user);
        
        	session.setMaxInactiveInterval(10*60);
        	VehicleDAO vDAO = new VehicleDAO();
        	try {
				v = vDAO.getAllVehicles(user.getID());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	session.setAttribute("vehicleList", v);
        	
        	/*session.setAttribute("vID", v.getID());
        	session.setAttribute("make", v.getMake());
        	session.setAttribute("model", v.getModel());
        	session.setAttribute("year", v.getYear());
        	session.setAttribute("mileage", v.getMileage());*/


            //request.setAttribute("userId", userID); // add to request
            //request.getSession().setAttribute("userId", userID); // add to session
            //this.getServletConfig().getServletContext().setAttribute("userID", userID); // add to application context
            //request.getRequestDispatcher("Welcome").forward(request, response);
            RequestDispatcher rs = request.getRequestDispatcher("Welcome.jsp");
            rs.forward(request, response);
        }
        else
        {
           out.println("Username or Password incorrect");
           RequestDispatcher rs = request.getRequestDispatcher("index.html");
           rs.include(request, response);
        }
		
	}

}
