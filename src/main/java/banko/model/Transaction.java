package banko.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int transacID;
	private String libelle;
	private String date;
	private int  montant;
	
	
	@ManyToOne
	private Compte compte_recepteur;
	
	@ManyToOne
	private Compte compte_emetteur;

	/**
	 * @return the transacID
	 */
	public int getTransacID() {
		return transacID;
	}

	/**
	 * @param transacID the transacID to set
	 */
	public void setTransacID(int transacID) {
		this.transacID = transacID;
	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param string the date to set
	 */
	public void setDate(String string) {
		this.date = string;
	}

	/**
	 * @return the montant
	 */
	public int getMontant() {
		return montant;
	}

	/**
	 * @param montant the montant to set
	 */
	public void setMontant(int montant) {
		this.montant = montant;
	}

	/**
	 * @return the compte_recepteur
	 */
	public Compte getCompte_recepteur() {
		return compte_recepteur;
	}

	/**
	 * @param compte_recepteur the compte_recepteur to set
	 */
	public void setCompte_recepteur(Compte compte_recepteur) {
		this.compte_recepteur = compte_recepteur;
	}

	/**
	 * @return the compte_emetteur
	 */
	public Compte getCompte_emetteur() {
		return compte_emetteur;
	}

	/**
	 * @param compte_emetteur the compte_emetteur to set
	 */
	public void setCompte_emetteur(Compte compte_emetteur) {
		this.compte_emetteur = compte_emetteur;
	}
	
  
}
