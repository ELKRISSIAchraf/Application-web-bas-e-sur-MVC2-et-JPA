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

import ma.fstt.JpaDao.CategorieJpaDao;
import ma.fstt.JpaDao.UserJpaDao;
import ma.fstt.entities.Categorie;
import ma.fstt.entities.User;

/**
 * Servlet implementation class CategorieController
 */
@WebServlet("/CategorieController")
public class CategorieController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategorieJpaDao catJpa=new CategorieJpaDao();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategorieController() {
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
                ajoutercategorie(request, response);
            	//response.getWriter().append("--------------------------------ajouter ").append(request.getContextPath());
                break; 
            case "ajouterCategorie":
                pageajoutercategorie(request, response);
            	//response.getWriter().append("--------------------------------ajouter ").append(request.getContextPath());
                break; 
            case "delete":
                deletecategorie(request, response);
                break;          
            case "update":
               updatecategorie(request, response);
                break;
            case "updateCategorie":
                pageupdatecategorie(request, response);
                 break;
            case "find":
                findcategorie(request, response);
                break;
            default:
            	response.getWriter().append("Served ---------------------------list-----------------: ").append(request.getContextPath());
                listCategories(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
	private void listCategories(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Categorie> listcategories = catJpa.getCategories();
        request.setAttribute("listcategories", listcategories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Categorie/categorie.jsp");
      dispatcher.forward(request, response);
    }
	private void ajoutercategorie(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String nom = request.getParameter("nomCat");
		
		Categorie cat = new Categorie(0L,nom);
		catJpa.ajouterCategorie(cat);
		response.sendRedirect("categorie?action=list");	
	}
	private void pageajoutercategorie(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {	
		 RequestDispatcher dispatcher = request.getRequestDispatcher("Categorie/addcategorie.jsp");
	      dispatcher.forward(request, response);
	}
	private void deletecategorie(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
	 Long id = Long.parseLong(request.getParameter("id"));	        System.out.println(id);
    //   Client client = new Client(id);
        catJpa.deleteCategorie(id);
        response.sendRedirect("categorie?action=list"); 
    }
 private void updatecategorie(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
	 Long id = Long.parseLong(request.getParameter("id"));
		String nom = request.getParameter("nomCat");
			 
       Categorie cat = new Categorie(  id,nom);
       catJpa.updateCategorie(cat);
        response.sendRedirect("categorie?action=list");  
	 }
 private void pageupdatecategorie(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	 Long id = Long.parseLong(request.getParameter("id"));
	Categorie cat =   catJpa.trouverById(id) ;
	  request.setAttribute("categorie", cat);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Categorie/updatecategorie.jsp");
      dispatcher.forward(request, response); 
	 }
 private void findcategorie(HttpServletRequest request, HttpServletResponse response) throws IOException
            {
	 Long id = Long.parseLong(request.getParameter("id"));
       Categorie cat = catJpa.trouverById(id);
        request.setAttribute("categorie",cat);
        System.out.println(cat);
        response.sendRedirect("categorie?action=list");
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
