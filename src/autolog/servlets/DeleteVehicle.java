package autolog.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import autolog.core.ServiceEntry;
import autolog.core.User;
import autolog.core.Vehicle;
import autolog.DAO.*;

/**
 * Servlet implementation class DeleteVehicle
 */
@WebServlet("/DeleteVehicle")
public class DeleteVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteVehicle() {
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
		// TODO Auto-generated method stub
		String srow = request.getParameter("row");
		int row = Integer.parseInt(srow);
				
		HttpSession session = request.getSession();
		
		ArrayList<Vehicle> vList = (ArrayList<Vehicle>) session.getAttribute("vehicleList");
		Vehicle vehicleToDelete = vList.get(row);
		
		ServiceEntryDAO seDAO = new ServiceEntryDAO();
		ArrayList<ServiceEntry> seList = new ArrayList<ServiceEntry>();
		try {
			seList = seDAO.getAllServiceEntries(vehicleToDelete.getID());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (int i=0 ; i<seList.size() ; i++) {
			ServiceEntry s = seList.get(i);
			try {
				seDAO.deleteServiceEntry(s.getID());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
        User u = (User) session.getAttribute("user");
		
        VehicleDAO vDAO = new VehicleDAO();
		
		try {
			vDAO.deleteVehicle(vehicleToDelete.getID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
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
