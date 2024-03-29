package Servlet;

import EJBInterface.UserEJBInterface;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by  Jaime on 27/03/2015.
 *
 */

public class IndexServlet extends EPEServlet {
	@EJB
	private UserEJBInterface userEJB;

	@Override
	protected void onGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		if(!isLogged(session)){resp.sendRedirect("session"); return;} //Refuse Un-logged

		showNotifications(req);
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/index.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void onPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
