package ma.fstt.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "panier")
public class Panier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_panier ;
	@Override
	public String toString() {
		return "Panier [id_panier=" + id_panier + ", id_user=" + id_user + ", id_prod=" + id_prod + "]";
	}
	public Panier() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Panier(Long id_panier, Long id_user, Long id_prod) {
		super();
		this.id_panier = id_panier;
		this.id_user = id_user;
		this.id_prod = id_prod;
	}
	public Long getId_panier() {
		return id_panier;
	}
	public void setId_panier(Long id_panier) {
		this.id_panier = id_panier;
	}
	public Long getId_user() {
		return id_user;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	public Long getId_prod() {
		return id_prod;
	}
	public void setId_prod(Long id_prod) {
		this.id_prod = id_prod;
	}
	private Long id_user;
	private Long id_prod;
}
