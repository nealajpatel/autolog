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

import autolog.DAO.ServiceEntryDAO;
import autolog.DAO.VehicleDAO;
import autolog.core.ServiceEntry;
import autolog.core.User;
import autolog.core.Vehicle;

/**
 * Servlet implementation class DeleteServiceEntry
 */
@WebServlet("/DeleteServiceEntry")
public class DeleteServiceEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServiceEntry() {
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
		
		System.out.println("Row: " + row);
		
		HttpSession session = request.getSession();
		
		ArrayList<ServiceEntry> seList = (ArrayList<ServiceEntry>) session.getAttribute("seList");
		
		ServiceEntry seToDelete = seList.get(row);
		
		//System.out.println(vehicleToDelete.getID());
		
        Vehicle v = (Vehicle) session.getAttribute("vehicle");
		
        ServiceEntryDAO seDAO = new ServiceEntryDAO();
		
		try {
			seDAO.deleteServiceEntry(seToDelete.getID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		try {
			seList = seDAO.getAllServiceEntries(v.getID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.setAttribute("seList", seList);
        
        RequestDispatcher rs = request.getRequestDispatcher("ServiceLog.jsp");
		
		rs.forward(request, response);
	}


}
