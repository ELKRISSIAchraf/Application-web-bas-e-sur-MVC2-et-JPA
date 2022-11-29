package ma.fstt.entities;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "produit")
public class Produit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_prod ;
	public Produit(Long id_prod, Long id_cat, String nom, String description, Double pu, int qte, String image) {
		super();
		this.id_prod = id_prod;
		this.id_cat = id_cat;
		this.nom = nom;
		this.description = description;
		this.pu = pu;
		this.qte = qte;
		this.image = image;
	}
	private Long id_cat ;
	public Long getId_cat() {
		return id_cat;
	}
	public void setId_cat(Long id_cat) {
		this.id_cat = id_cat;
	}
	public Long getId_prod() {
		return id_prod;
	}
	public void setId_prod(Long id_prod) {
		this.id_prod = id_prod;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPu() {
		return pu;
	}
	public void setPu(Double pu) {
		this.pu = pu;
	}
	public int getQte() {
		return qte;
	}
	public void setQte(int qte) {
		this.qte = qte;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Column(length = 255, nullable = true)
	private String nom;

	@Column(length = 255, nullable = true)
	private String description ;
	@Column( nullable = true)
	private Double pu;
	public Produit() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Column( nullable = true)
	private int qte;


	@Column( nullable = true)
	private String image;
	@Override
	public String toString() {
		return "Produit [id_prod=" + id_prod + ", id_cat=" + id_cat + ", nom=" + nom + ", description=" + description
				+ ", pu=" + pu + ", qte=" + qte + ", image=" + image + "]";
	}
}
