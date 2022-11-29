package ma.fstt.servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import ma.fstt.JpaDao.CategorieJpaDao;
import ma.fstt.JpaDao.ProduitJpaDao;
import ma.fstt.entities.Categorie;
import ma.fstt.entities.Produit;


/**
 * Servlet implementation class ProduitController
 */
@WebServlet("/ProduitController")
@MultipartConfig
public class ProduitController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProduitJpaDao produitJpa=new ProduitJpaDao();
	private CategorieJpaDao categorieJpa = new CategorieJpaDao();
    public static final int TAILLE_TAMPON = 10240;
    public static final String CHEMIN_FICHIERS = "C:\\Users\\HP\\Desktop\\ACHAAK\\atelier2\\src\\main\\webapp\\images\\";
    /**
     * @see HttpServlet#HttpServlet() 
     */
    public ProduitController() {
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
                ajouterproduit(request, response);
            	//response.getWriter().append("--------------------------------ajouter ").append(request.getContextPath());
                break; 
            case "ajouterProduit":
                pageajouterproduit(request, response);
            	//response.getWriter().append("--------------------------------ajouter ").append(request.getContextPath());
                break; 
            case "updateProduit":
                pageupdateproduit(request, response);
            	//response.getWriter().append("--------------------------------ajouter ").append(request.getContextPath());
                break; 
            case "delete":
                deleteproduit(request, response);
                break;          
            case "update":
               updateproduit(request, response);
                break;
            case "find":
                findproduit(request, response);
                break;
                
            default:
            	response.getWriter().append("Served ---------------------------list-----------------: ").append(request.getContextPath());
                listProduits(request, response);
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
	private void listProduits(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Produit> listproduits = produitJpa.getProduits();
       List<Categorie> listcategories =categorieJpa.getCategories();
      
       request.setAttribute("listproduits", listproduits);
       request.setAttribute("listcategories", listcategories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Produit/produit.jsp");
      dispatcher.forward(request, response);
    }
	private void ajouterproduit(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String nom = request.getParameter("nomProd");
		String description = request.getParameter("descriptionProd");
		Double pu = Double.parseDouble(request.getParameter("pu"));	
		int qte = Integer.parseInt(request.getParameter("qte"));
		Long idCat = Long.parseLong(request.getParameter("idCat"));	        
		/*  Part part=request.getPart("image");
        //--------------- enregistrer l'image----------------------------------
        String fileName=part.getSubmittedFileName();
        String path=getServletContext().getRealPath("/"+"images"+File.separator+fileName);
        InputStream is = part.getInputStream();
        try {
			byte[] byt =new byte[is.available()];
			is.read();
			FileOutputStream fops = new FileOutputStream(path);
			fops.write(byt);
			fops.flush();
			fops.close();
		} catch (Exception e) {
			e.getStackTrace();
		}*/
	// -----------------------------------------------ENREGISTREMENT D'IMAGE-------------------------------------------
        // On récupère le champ du fichier
        Part part = request.getPart("image");
            
        // On vérifie qu'on a bien reçu un fichier
        String nomFichier = getNomFichier(part);

        // Si on a bien un fichier
        if (nomFichier != null && !nomFichier.isEmpty()) {
            String nomChamp = part.getName();
            // Corrige un bug du fonctionnement d'Internet Explorer
             nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
                    .substring(nomFichier.lastIndexOf('\\') + 1);

            // On écrit définitivement le fichier sur le disque
            ecrireFichier(part, nomFichier, CHEMIN_FICHIERS);
		Produit prod = new Produit(0L,idCat,nom, description, pu,qte,nomFichier);
		produitJpa.ajouterProduit(prod);
		
		response.sendRedirect("produit?action=list");	
	}}

	private void pageajouterproduit(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {	
		 List<Categorie> listcategories =categorieJpa.getCategories();
	      
	       
	       request.setAttribute("listcategories", listcategories);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Produit/addproduit.jsp");
	      dispatcher.forward(request, response);
	}
	 private void deleteproduit(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException {
		 Long id = Long.parseLong(request.getParameter("id"));	        
	    //   Client client = new Client(id);
	        produitJpa.deleteProduit(id);
	        response.sendRedirect("produit?action=list"); 
	    }
	 private void updateproduit(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		 Long id = Long.parseLong(request.getParameter("id"));	        

		 String nom = request.getParameter("nomProd");
			String description = request.getParameter("descriptionProd");
			Double pu = Double.parseDouble(request.getParameter("pu"));	
			int qte = Integer.parseInt(request.getParameter("qte"));
			Long idCat = Long.parseLong(request.getParameter("idCat"));	        
	       
	        //--------------- enregistrer l'image----------------------------------
	        // On récupère le champ du fichier
	        Part part = request.getPart("image");
	            
	        // On vérifie qu'on a bien reçu un fichier
	        String nomFichier = getNomFichier(part);

	        // Si on a bien un fichier
	        if (nomFichier != null && !nomFichier.isEmpty()) {
	            String nomChamp = part.getName();
	            // Corrige un bug du fonctionnement d'Internet Explorer
	             nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
	                    .substring(nomFichier.lastIndexOf('\\') + 1);

	            // On écrit définitivement le fichier sur le disque
	            ecrireFichier(part, nomFichier, CHEMIN_FICHIERS);
			Produit prod = new Produit(id,idCat,nom, description, pu,qte,nomFichier);
	        produitJpa.updateProduit(prod) ;
	        response.sendRedirect("produit?action=list");  
		 }}
	 private void pageupdateproduit(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		 Long id = Long.parseLong(request.getParameter("id"));
		 Produit prod =   produitJpa.trouverById(id) ;
		 List<Categorie> listcategories =categorieJpa.getCategories();    
	       request.setAttribute("listcategories", listcategories);
		  request.setAttribute("produit", prod);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("Produit/updateproduit.jsp");
	      dispatcher.forward(request, response); 
		 }
	 private void findproduit(HttpServletRequest request, HttpServletResponse response) throws IOException
     {
Long id = Long.parseLong(request.getParameter("id"));
Produit prod = produitJpa.trouverById(id);
 request.setAttribute("produit", prod);
 System.out.println(prod);
 response.sendRedirect("produit?action=list");
}
	 //---------------------------------------------DES FONCTIONS POUR L'IMAGE ---------------------------------------
     private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
         BufferedInputStream entree = null;
         BufferedOutputStream sortie = null;
         try {
             entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
             System.out.println(chemin + nomFichier);
             sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);
System.out.println(chemin + nomFichier);
             byte[] tampon = new byte[TAILLE_TAMPON];
             int longueur;
             while ((longueur = entree.read(tampon)) > 0) {
                 sortie.write(tampon, 0, longueur);
             }
         } finally {
             try {
                 sortie.close();
             } catch (IOException ignore) {
             }
             try {
                 entree.close();
             } catch (IOException ignore) {
             }
         }
     }
     private static String getNomFichier( Part part ) {
         for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
             if ( contentDisposition.trim().startsWith( "filename" ) ) {
                 return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
             }
         }
         return null;
     }   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
