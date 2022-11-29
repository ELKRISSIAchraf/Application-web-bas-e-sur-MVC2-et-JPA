package ma.fstt.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ma.fstt.JpaDao.CategorieJpaDao;
import ma.fstt.JpaDao.PanierJpaDao;
import ma.fstt.JpaDao.ProduitJpaDao;
import ma.fstt.JpaDao.UserJpaDao;
import ma.fstt.entities.Categorie;
import ma.fstt.entities.Panier;
import ma.fstt.entities.Produit;
import ma.fstt.entities.User;

/**
 * Servlet implementation class frontController
 */
@WebServlet("/frontController")
public class frontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProduitJpaDao produitJpa=new ProduitJpaDao();
	private CategorieJpaDao categorieJpa = new CategorieJpaDao();
	private UserJpaDao userJpa=new UserJpaDao();
	private PanierJpaDao panierJpa=new PanierJpaDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public frontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");	
		HttpSession session = request.getSession(true);
		String nom =  (String) session.getAttribute("nom");
        try {
            switch (action) {
            case "monpanier":
                if(nom==null) response.sendRedirect("frontController?action=login"); 
                else  monpanier(request, response);
                break;
            case "addtopanier":	
                if(nom==null) response.sendRedirect("frontController?action=login"); 
                else  addtopanier(request, response);
                break;
            case "deletefrompanier":	
               
                 deletefrompanier(request, response);
                break;
            case "login":
            	
            	 RequestDispatcher dispatcher = request.getRequestDispatcher("front/login.jsp");
            	   dispatcher.forward(request, response);

            	//response.getWriter().append("--------------------------------ajouter ").append(request.getContextPath());
                break;
            case "logout":
            	session.invalidate();
            	response.sendRedirect("frontController?action=list"); 

           	//response.getWriter().append("--------------------------------ajouter ").append(request.getContextPath());
               break;
            case "loginSession":
            	
                 loginsession(request, response);
               break;
            case "register":
           	 RequestDispatcher dispatcher11 = request.getRequestDispatcher("front/register.jsp");
           	   dispatcher11.forward(request, response);
           	//response.getWriter().append("--------------------------------ajouter ").append(request.getContextPath());
               break;
            case "registerSession":
         registersession(request, response);

              	//response.getWriter().append("--------------------------------ajouter ").append(request.getContextPath());
                  break;
            case "categorie":
                categorie(request, response);
            	
                break;              
            default:
            	response.getWriter().append("Served ---------------------------list-----------------: ").append(request.getContextPath());
                accueil(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}
private void accueil(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
	 List<Produit> listproduits = produitJpa.getProduits();
	 request.setAttribute("listproduits", listproduits);
	List<Categorie> listcategories = categorieJpa.getCategories();    
     request.setAttribute("listcategories", listcategories);
     RequestDispatcher dispatcher = request.getRequestDispatcher("front/pageaccueil.jsp");
   dispatcher.forward(request, response);
}
private void categorie(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException{
	 Long id = Long.parseLong(request.getParameter("id"));	 
	 List<Produit> listproduits = produitJpa.getProduits();
	 List<Produit> listproduits2 = new ArrayList<>() ;
for (Produit produit : listproduits) {
	if (produit.getId_cat()==id) listproduits2.add(produit)  ;
}
List<Categorie> listcategories = categorieJpa.getCategories();    
request.setAttribute("listcategories", listcategories);
 request.setAttribute("listproduits", listproduits2);
 RequestDispatcher dispatcher = request.getRequestDispatcher("front/categorie.jsp");
dispatcher.forward(request, response);
System.out.println(listproduits2);
}
private void loginsession(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException{
String email= request.getParameter("email");
String password= request.getParameter("password");
HttpSession session=request.getSession();
User user= userJpa.TrouverByNom(email, password);
if(user.getRole()==1) 	        response.sendRedirect("produit?action=list"); 
else if(user != null) {
session.setAttribute("nom", user.getNom());
session.setAttribute("id", user.getId_user());
response.sendRedirect("frontController?action=list"); 
}
}
private void registersession(HttpServletRequest request, HttpServletResponse response)
 throws SQLException, IOException, ServletException{
String nom= request.getParameter("nom");
String email= request.getParameter("email");
String password= request.getParameter("password");
User user = new User(0L,nom, email, password,2);
userJpa.ajouterUser(user);
HttpSession session=request.getSession();
User use= userJpa.TrouverByNom(email, password);

if(use != null) {
session.setAttribute("nom", user.getNom());
session.setAttribute("id", user.getId_user());
response.sendRedirect("frontController?action=list"); 
}
}
private void monpanier(HttpServletRequest request, HttpServletResponse response)
		 throws SQLException, IOException, ServletException{
	 List<Panier> listpaniers1 = panierJpa.getPaniers(); 
	 List<Panier> listpaniers = new ArrayList<Panier>(); 
	 HttpSession session = request.getSession(true);
 	Long idUser =  (Long) session.getAttribute("id");
 	for (Panier panier : listpaniers1) {
		if(panier.getId_user()==idUser) listpaniers.add(panier);
	}
 	

 	 List<Produit> listproduits = produitJpa.getProduits();
	 request.setAttribute("listproduits", listproduits);
	List<Categorie> listcategories = categorieJpa.getCategories();    
     request.setAttribute("listcategories", listcategories);
	 request.setAttribute("listpaniers", listpaniers1);
	  System.out.println(listpaniers);
	  System.out.println(listproduits);
	  System.out.println(listpaniers1);
	  
        RequestDispatcher dispatcher = request.getRequestDispatcher("front/panier.jsp");
      dispatcher.forward(request, response);
}
private void addtopanier(HttpServletRequest request, HttpServletResponse response)
		 throws SQLException, IOException, ServletException{
	 HttpSession session = request.getSession(true);
	 Long idUser =  (Long) session.getAttribute("id");
	Long idProd = Long.parseLong(request.getParameter("id"));	        
	Panier panier = new Panier(0L,idUser, idProd);
	panierJpa.ajouterPanier(panier);
	response.sendRedirect("frontController?action=monpanier");
}
private void deletefrompanier(HttpServletRequest request, HttpServletResponse response)
		 throws SQLException, IOException, ServletException{
	 Long id = Long.parseLong(request.getParameter("id"));	        
	        panierJpa.deletePanier(id);    
	response.sendRedirect("frontController?action=monpanier");
}
}
