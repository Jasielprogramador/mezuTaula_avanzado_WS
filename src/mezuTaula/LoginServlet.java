package mezuTaula;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helper.db.MYSQLdb;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MYSQLdb mysqldb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter http_out = response.getWriter();
		
		System.out.println("---> Login servlet-ean sartzen...");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if(email != null && password != null) {
			
			String username = mysqldb.getUsername(email, password);
		
			if(username!=null) {
				
				System.out.println("\tEmail : "+email);
				System.out.println("Pasahitza : "+password);
				
				//Si le pasas true saioa bat sortuta ez badago te crea uno y si ya uno esta sortuta te utiliza uno
				
				System.out.println("Login OK : redirecting to MainServlet...");
				HttpSession session = request.getSession(true);
				
				//sesion te agrupa todas las escaeras
				
				//durante toda la sesion quieres saber quien es el erabiltzaile por eso lo pones
				session.setAttribute("username",username);
				
				String sessionID = session.getId();
				System.out.println("\tUser session for " + username + ": " + sessionID);
				
				//asi consigues el servlet por su nombre
				ServletContext context = request.getServletContext();
				RequestDispatcher rd = context.getNamedDispatcher("MainServlet");
				rd.forward(request, response);
				
			}
			else {
				System.out.println("Login error : Redirecting to login form...");
				boolean login_error = true;
				
				//HTTP eskaera batean 2 gauza egon daitezke atributuak eta parametroak
				//parametroak HTTP eskaeraren propioak dira, eta aldiz atributuak guk gehitzen ditugunak dira
				request.setAttribute("login_error", login_error);
				
				//La uri aqui es relativa, porque es como si estuviera dentro del directorio de proba1
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/login_form.jsp");
				
				//forward te hace el redireccionamiento
				rd.forward(request, response);
				
				System.out.println("Redirected to login form...");
				
			}
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/login_form.jsp");
			rd.forward(request, response);
		}
		
		
		System.out.println("---> Login servletik ateratzen...");
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
