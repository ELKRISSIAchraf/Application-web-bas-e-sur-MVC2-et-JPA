package ma.fstt.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.fstt.JpaDao.PanierJpaDao;
import ma.fstt.JpaDao.UserJpaDao;
import ma.fstt.entities.Categorie;
import ma.fstt.entities.Panier;
import ma.fstt.entities.User;

/**
 * Servlet implementation class PanierController
 */
@WebServlet("/PanierController")
public class PanierController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PanierJpaDao panierJpa=new PanierJpaDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PanierController() {
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
                ajouterpanier(request, response);
            	//response.getWriter().append("--------------------------------ajouter ").append(request.getContextPath());
                break;                       
            case "delete":
                deletepanier(request, response);
                break;              
            default:
            	response.getWriter().append("Served ---------------------------list-----------------: ").append(request.getContextPath());
                listPaniers(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
	}
	private void listPaniers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Panier> listpaniers = panierJpa.getPaniers(); 
    
      
     /*   request.setAttribute("listclients", listclients);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Client/client.jsp");
      dispatcher.forward(request, response);*/
    }
	private void ajouterpanier(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		 Long idUser = Long.parseLong(request.getParameter("idUser"));	       
		 Long idProd = Long.parseLong(request.getParameter("idProd"));	        
		Panier panier = new Panier(0L,idUser, idProd);
		panierJpa.ajouterPanier(panier);
		response.sendRedirect("panier?action=list");	
		 
	}
	 private void deletepanier(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException {
		 Long id = Long.parseLong(request.getParameter("id"));	       
	    //   Client client = new Client(id);
	        panierJpa.deletePanier(id);
	        response.sendRedirect("panier?action=list"); 
	    }
	/* private void updatepanier(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		 Long id = Long.parseLong(request.getParameter("id"));
			String nom = request.getParameter("nomUser");
			String email = request.getParameter("emailUser");
			String password = request.getParameter("passwordUser");
		 
	        User user = new User(id,nom, email, password,2);
	        userJpa.updateUser(user) ;
	        response.sendRedirect("user?action=list");  
		 }*/
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
