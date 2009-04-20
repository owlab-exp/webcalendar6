package edu.cmu.tsp6.server.notification;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InitializeMailServlet
 */
public class InitializeNotifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NotificationTimer timer = null; 

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitializeNotifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init() throws ServletException {
    	timer = new NotificationTimer();
    	timer.proc();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	public void destroy() {
		super.destroy();
		timer.cancel();
	}
}
