package autolog.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
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
 * Servlet implementation class AddServiceEntry
 */
@WebServlet("/AddServiceEntry")
public class AddServiceEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServiceEntry() {
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
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String desc = request.getParameter("desc");
        String sdate = request.getParameter("date");
        String scost = request.getParameter("cost");
        String smileage = request.getParameter("mileage");
        
        Date date = Date.valueOf(sdate);
        int mileage = Integer.parseInt(smileage);
        
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');
        String pattern = "#,##0.0#";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);

        // parse the string
        BigDecimal cost = new BigDecimal(0.00);
		try {
			cost = (BigDecimal) decimalFormat.parse(scost);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        ServiceEntryDAO seDAO = new ServiceEntryDAO();
        
        HttpSession session = request.getSession();

        
        Vehicle v = (Vehicle) session.getAttribute("vehicle");
        
        try {
			seDAO.addServiceEntry(v.getID(), desc, date, cost, mileage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if (mileage > v.getMileage()) {
        	VehicleDAO vDAO = new VehicleDAO();
        	try {
				vDAO.editVehicle(v.getID(), v.getMake(), v.getModel(), v.getYear(), mileage);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	User u = (User) session.getAttribute("user");
        	ArrayList<Vehicle> vList = new ArrayList<Vehicle>();
        	try {
				vList = vDAO.getAllVehicles(u.getID());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	int row = (int) session.getAttribute("vrow");
        	
        	session.setAttribute("vehicleList", vList);
        	session.setAttribute("vehicle", vList.get(row));
        	
        }
        
    	ArrayList<ServiceEntry> se = new ArrayList<ServiceEntry>();
       try {
    	   se = seDAO.getAllServiceEntries(v.getID());
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        
        session.setAttribute("seList", se);
        
        RequestDispatcher rs = request.getRequestDispatcher("ServiceLog.jsp");
		
		rs.forward(request, response);
	}

}
