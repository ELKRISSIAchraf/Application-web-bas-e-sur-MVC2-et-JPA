package ma.fstt.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.fstt.JpaDao.UserJpaDao;
import ma.fstt.entities.User;


/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserJpaDao userJpa=new UserJpaDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");	 
        try {
            switch (action) {
            case "ajouter":
                ajouteruser(request, response);
            	//response.getWriter().append("--------------------------------ajouter ").append(request.getContextPath());
                break; 
            case "ajouterUser":
                pageajouteruser(request, response);
            	//response.getWriter().append("--------------------------------ajouter ").append(request.getContextPath());
                break; 
            case "updateUser":
                pageupdateuser(request, response);
            	//response.getWriter().append("--------------------------------ajouter ").append(request.getContextPath());
                break; 
            case "delete":
                deleteuser(request, response);
                break;          
            case "update":
               updateuser(request, response);
                break;
            case "find":
                finduser(request, response);
                break;
                
            default:
            	response.getWriter().append("Served ---------------------------list-----------------: ").append(request.getContextPath());
                listUsers(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
	private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listusers = userJpa.getUsers();
       
        //response.getWriter().append((CharSequence) listclients).append(request.getContextPath());
       //RequestDispatcher dispatcher = request.getRequestDispatcher(null);
        // dispatcher.forward(request, response);
       /* System.out.println(listusers);
        PrintWriter out= response.getWriter();
        out.println("<html><body>");
        out.println("<li> Nom:"+listusers.toString()+"</li>");
        out.println("</html></body>");*/
       request.setAttribute("listusers", listusers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("User/user.jsp");
      dispatcher.forward(request, response);
    }
	private void ajouteruser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String nom = request.getParameter("nomUser");
		String email = request.getParameter("emailUser");
		String password = request.getParameter("passwordUser");
		int role = Integer.parseInt(request.getParameter("roleUser"));	        
		User user = new User(0L,nom, email, password,role);
		userJpa.ajouterUser(user);
		response.sendRedirect("user?action=list");  	
	}
	private void pageajouteruser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {	
		 RequestDispatcher dispatcher = request.getRequestDispatcher("User/adduser.jsp");
	      dispatcher.forward(request, response);
	}
	 private void deleteuser(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException {
		 Long id = Long.parseLong(request.getParameter("id"));	        
	    //   Client client = new Client(id);
	        userJpa.deleteUser(id);
	        response.sendRedirect("user?action=list"); 
	    }
	 private void updateuser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		 Long id = Long.parseLong(request.getParameter("id"));
			String nom = request.getParameter("nomUser");
			String email = request.getParameter("emailUser");
			String password = request.getParameter("passwordUser");
			int role = Integer.parseInt(request.getParameter("roleUser"));	        

	        User user = new User(id,nom, email, password,role);
	        userJpa.updateUser(user) ;
	        response.sendRedirect("user?action=list");  
		 }
	 private void pageupdateuser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		 Long id = Long.parseLong(request.getParameter("id"));
		 User user =   userJpa.trouverById(id) ;
		  request.setAttribute("user", user);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("User/updateuser.jsp");
	      dispatcher.forward(request, response); 
		 }
	 private void finduser(HttpServletRequest request, HttpServletResponse response) throws IOException
	            {
		 Long id = Long.parseLong(request.getParameter("id"));
	       User user = userJpa.trouverById(id);
	        request.setAttribute("user", user);
	        System.out.println(user);
	        response.sendRedirect("user?action=list");
	    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
