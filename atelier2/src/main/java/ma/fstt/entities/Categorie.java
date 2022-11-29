package ma.fstt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorie")
public class Categorie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_cat ;
	@Override
	public String toString() {
		return "Categorie [id_cat=" + id_cat + ", nom=" + nom + "]";
	}
	public Categorie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Categorie(Long id_cat, String nom) {
		super();
		this.id_cat = id_cat;
		this.nom = nom;
	}
	public Long getId_cat() {
		return id_cat;
	}
	public void setId_cat(Long id_cat) {
		this.id_cat = id_cat;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	@Column(length = 255, nullable = true)
	private String nom;
}
