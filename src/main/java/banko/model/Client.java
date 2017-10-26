package banko.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.JsonAdapter;

@Entity
public class Client {
	
	private String nom;
	private String prenom;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int clientID;
	private String passwd;
	private String login;
	
	@OneToMany(mappedBy="client", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private transient List<Compte> comptes;
	
	
	public String toString() {
		return clientID + " "+nom+" "+prenom+" "+login+" "+passwd;
	}


	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}


	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}


	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}


	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	/**
	 * @return the clientID
	 */
	public int getClientID() {
		return clientID;
	}


	/**
	 * @param clientID the clientID to set
	 */
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}


	/**
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}


	/**
	 * @param passwd the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}


	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}


	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}


	/**
	 * @return the comptes
	 */
	public List<Compte> getComptes() {
		return comptes;
	}


	/**
	 * @param comptes the comptes to set
	 */
	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}



}
