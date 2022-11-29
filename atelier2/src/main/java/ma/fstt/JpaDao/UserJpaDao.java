package ma.fstt.JpaDao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import com.mysql.cj.Query;
import ma.fstt.entities.User;
public class UserJpaDao {
	private static final String PERSISTENCE_UNIT_NAME = "unit";	
	private static EntityManager entityMgrObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	private static EntityTransaction transactionObj = entityMgrObj.getTransaction();	
	public void ajouterUser(User user) {
		if(!transactionObj.isActive()) {
			transactionObj.begin();
		}
        String nom = user.getNom();
        String email = user.getEmail();
        String password =user.getPassword();
		User u = new User(0L,nom , email,password,user.getRole());	
		entityMgrObj.persist(u);
		transactionObj.commit();
	};
	public List<User> getUsers()   {
		javax.persistence.Query queryObj =  entityMgrObj.createQuery("SELECT e FROM User e ");
	@SuppressWarnings("unchecked")
	List<User> lList = ((javax.persistence.Query) queryObj).getResultList();
	if (lList != null && lList.size() > 0) {			
		return lList;
	} else {
		return null;
	}};
	public void deleteUser(Long id)  {
		if(!transactionObj.isActive()) {
			transactionObj.begin();
		}
		User u =entityMgrObj.find(User.class, id);
		entityMgrObj.remove(u);
		transactionObj.commit();		
	} ;
	public void updateUser(User user ) {		
		User u =entityMgrObj.find(User.class, user.getId_user());
		u.setNom(user.getNom());
		u.setEmail(user.getEmail());
		u.setPassword(user.getPassword());
		u.setRole(user.getRole());
	};
	public User trouverById(Long id)  {if(!transactionObj.isActive()) {
		transactionObj.begin();
	}
	return entityMgrObj.find(User.class, id);}
	public User TrouverByNom(String email,String password) {
		
javax.persistence.Query queryObj =  entityMgrObj.createQuery("SELECT e FROM User e WHERE e.email = :email AND e.password= :password");
queryObj.setParameter("email", email);
queryObj.setParameter("password", password);
User u = (User) ((javax.persistence.Query) queryObj).getSingleResult();
if (u != null ) {			
	return u;
} else {
	return null;
}
		
		
		
	}
}
