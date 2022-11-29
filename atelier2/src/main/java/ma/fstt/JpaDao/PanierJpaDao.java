package ma.fstt.JpaDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.mysql.cj.Query;

import ma.fstt.entities.Panier;

public class PanierJpaDao {
	private static final String PERSISTENCE_UNIT_NAME = "unit";	
	private static EntityManager entityMgrObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	private static EntityTransaction transactionObj = entityMgrObj.getTransaction();	
	public void ajouterPanier(Panier pan) {
		if(!transactionObj.isActive()) {
			transactionObj.begin();
		}
        Long idUser = pan.getId_user();
        Long idPro=pan.getId_prod();       
        Panier pani = new Panier(0L,idUser,idPro);	
		entityMgrObj.persist(pani);
		transactionObj.commit();
	};
	public List<Panier> getPaniers()   {
		javax.persistence.Query queryObj = entityMgrObj.createQuery("SELECT e FROM Panier e");
	@SuppressWarnings("unchecked")
	List<Panier> lList = ((javax.persistence.Query) queryObj).getResultList();
	if (lList != null && lList.size() > 0) {			
		return lList;
	} else {
		return null;
	}};
	public void deletePanier(Long id)  {
		if(!transactionObj.isActive()) {
			transactionObj.begin();
		}
		Panier p =entityMgrObj.find(Panier.class, id);
		entityMgrObj.remove(p);
		transactionObj.commit();		
	} ;
	public void updatePanier(Panier pan ) {		
		Panier p =entityMgrObj.find(Panier.class, pan.getId_panier());
		p.setId_user(pan.getId_user());
		p.setId_prod(pan.getId_prod());
	};
	public Panier trouverById(Long id)  {if(!transactionObj.isActive()) {
		transactionObj.begin();
	}
	return entityMgrObj.find(Panier.class,id);}
}
