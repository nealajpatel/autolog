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

import autolog.core.ServiceEntry;
import autolog.core.Vehicle;

/**
 * Servlet implementation class EditServiceEntry
 */
@WebServlet("/EditServiceEntry")
public class EditServiceEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServiceEntry() {
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
	 * Servlet requests service entry to be edited and sets it as a session attribute.
	 * A jsp is called so the user can update the service entry
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String srow = request.getParameter("row");
		int row = Integer.parseInt(srow);
				
		HttpSession session = request.getSession();
		
		ArrayList<ServiceEntry> seList = (ArrayList<ServiceEntry>) session.getAttribute("seList");
		ServiceEntry seToEdit = seList.get(row);
		
		session.setAttribute("seToEdit", seToEdit);
		
		RequestDispatcher rs = request.getRequestDispatcher("EditServiceEntry.jsp");
        rs.forward(request, response);
	}

}
