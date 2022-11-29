package ma.fstt.JpaDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.mysql.cj.Query;

import ma.fstt.entities.Categorie;


public class CategorieJpaDao {
	private static final String PERSISTENCE_UNIT_NAME = "unit";	
	private static EntityManager entityMgrObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	private static EntityTransaction transactionObj = entityMgrObj.getTransaction();	
	public void ajouterCategorie(Categorie cat) {
		if(!transactionObj.isActive()) {
			transactionObj.begin();
		}
        String nom = cat.getNom();
        Categorie cate = new Categorie(0L,nom);	
		entityMgrObj.persist(cate);
		transactionObj.commit();
	};
	public List<Categorie> getCategories()   {
		javax.persistence.Query queryObj =  entityMgrObj.createQuery("SELECT e FROM Categorie e");
	@SuppressWarnings("unchecked")
	List<Categorie> lList = ((javax.persistence.Query) queryObj).getResultList();
	if (lList != null && lList.size() > 0) {			
		return lList;
	} else {
		return null;
	}};
	public void deleteCategorie(Long id)  {
		if(!transactionObj.isActive()) {
			transactionObj.begin();
		}
		Categorie u =entityMgrObj.find(Categorie.class, id);
		entityMgrObj.remove(u);
		transactionObj.commit();		
	} ;
	public void updateCategorie(Categorie cat ) {		
		Categorie u =entityMgrObj.find(Categorie.class, cat.getId_cat());
		u.setNom(cat.getNom());
		
	};
	public Categorie trouverById(Long id)  {if(!transactionObj.isActive()) {
		transactionObj.begin();
	}
	return entityMgrObj.find(Categorie.class,id);}
}

