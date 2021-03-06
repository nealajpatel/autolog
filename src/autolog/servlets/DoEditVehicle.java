package autolog.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import autolog.DAO.VehicleDAO;
import autolog.core.User;
import autolog.core.Vehicle;

/**
 * Servlet implementation class DoEditVehicle
 */
@WebServlet("/DoEditVehicle")
public class DoEditVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoEditVehicle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String make = request.getParameter("make");
        String model = request.getParameter("model");
        String syear = request.getParameter("year");
        String smileage = request.getParameter("mileage");
        
        int year = Integer.parseInt(syear);
        int mileage = Integer.parseInt(smileage);
        
        VehicleDAO vDAO = new VehicleDAO();
        
        HttpSession session = request.getSession();

        Vehicle v = (Vehicle) session.getAttribute("vehicleToEdit");
        
        try {
			vDAO.editVehicle(v.getID(), make, model, year, mileage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ArrayList<Vehicle> vList = new ArrayList<Vehicle>();
    	User u = (User) session.getAttribute("user");
       try {
    	   vList = vDAO.getAllVehicles(u.getID());
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        
        session.setAttribute("vehicleList", vList);
        
        RequestDispatcher rs = request.getRequestDispatcher("Welcome.jsp");
		
		rs.forward(request, response);
	}

}
