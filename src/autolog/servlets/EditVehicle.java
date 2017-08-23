package autolog.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import autolog.core.Vehicle;

/**
 * Servlet implementation class EditVehicle
 */
@WebServlet("/EditVehicle")
public class EditVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditVehicle() {
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
		Vehicle vehicleToEdit = vList.get(row);
		
		session.setAttribute("vehicleToEdit", vehicleToEdit);
		
		RequestDispatcher rs = request.getRequestDispatcher("EditVehicle.jsp");
        rs.forward(request, response);
		
	}

}
