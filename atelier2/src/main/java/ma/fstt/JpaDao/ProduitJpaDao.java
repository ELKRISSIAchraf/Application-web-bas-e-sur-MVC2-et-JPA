package ma.fstt.JpaDao;

import java.sql.Blob;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.mysql.cj.Query;

import ma.fstt.entities.Produit;

public class ProduitJpaDao {
	private static final String PERSISTENCE_UNIT_NAME = "unit";	
	private static EntityManager entityMgrObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	private static EntityTransaction transactionObj = entityMgrObj.getTransaction();	
	public void ajouterProduit(Produit prod) {
		if(!transactionObj.isActive()) {
			transactionObj.begin();
		}
		Long idCat=prod.getId_cat();
        String nom = prod.getNom();
        String description = prod.getDescription();
        Double pu =prod.getPu();
        int qte = prod.getQte();
        String image =prod.getImage();
        Produit u = new Produit(0L,idCat,nom ,description,pu,qte,image);	
		entityMgrObj.persist(u);
		transactionObj.commit();
	};
	public List<Produit> getProduits()   {
		javax.persistence.Query queryObj =  entityMgrObj.createQuery("SELECT e FROM Produit e");
	@SuppressWarnings("unchecked")
	List<Produit> lList = ((javax.persistence.Query) queryObj).getResultList();
	if (lList != null && lList.size() > 0) {			
		return lList;
	} else {
		return null;
	}};
	public void deleteProduit(Long id)  {
		if(!transactionObj.isActive()) {
			transactionObj.begin();
		}
		Produit u =entityMgrObj.find(Produit.class, id);
		entityMgrObj.remove(u);
		transactionObj.commit();		
	} ;
	public void updateProduit(Produit prod ) {		
		Produit p =entityMgrObj.find(Produit.class, prod.getId_prod());
		p.setNom(prod.getNom());
		p.setId_cat(prod.getId_cat());
		p.setDescription(prod.getDescription());
		p.setPu(prod.getPu());
		p.setQte(prod.getQte());
		p.setImage(prod.getImage());
	};
	public Produit trouverById(Long id)  {if(!transactionObj.isActive()) {
		transactionObj.begin();
	}
	return entityMgrObj.find(Produit.class, id);}
}
