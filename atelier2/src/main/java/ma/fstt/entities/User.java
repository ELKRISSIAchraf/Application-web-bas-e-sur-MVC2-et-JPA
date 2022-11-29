package ma.fstt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")


public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_user ;
	
	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", nom=" + nom + ", email=" + email + ", password=" + password + ", role="
				+ role + "]";
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id_user, String nom, String email, String password, int role) {
		super();
		this.id_user = id_user;
		this.nom = nom;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Column(length = 255, nullable = true)
	private String nom;
	
	@Column( length = 255,nullable = true)
	private String  email;

	@Column( length = 255,nullable = true)
	private String  password;

	@Column( nullable = true)
	private int  role;
}
