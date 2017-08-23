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
import javax.websocket.Session;

import autolog.DAO.ServiceEntryDAO;
import autolog.core.ServiceEntry;
import autolog.core.User;
import autolog.core.Vehicle;

/**
 * Servlet implementation class ServiceEntries
 */
@WebServlet("/ServiceEntries")
public class ServiceEntries extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceEntries() {
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
		
		session.setAttribute("vrow", row);
		
		ArrayList<Vehicle> vList = (ArrayList<Vehicle>) session.getAttribute("vehicleList");
		
		Vehicle vehicleForServiceEntries = vList.get(row);
		
		//System.out.println(vehicleToDelete.getID());
		
        session.setAttribute("vehicle", vehicleForServiceEntries);
        
        ServiceEntryDAO seDAO = new ServiceEntryDAO();
        
		ArrayList<ServiceEntry> seList = new ArrayList<ServiceEntry>();
        
        try {
			seList = seDAO.getAllServiceEntries(vehicleForServiceEntries.getID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if (seList != null) {
             
         	 session.setAttribute("seList", seList);
              
         	 RequestDispatcher rs = request.getRequestDispatcher("ServiceLog.jsp");
             rs.forward(request, response);
        } else {
                
    	RequestDispatcher rs = request.getRequestDispatcher("AddServiceEntry.jsp");
        rs.forward(request, response);
        
        }
	}

}
