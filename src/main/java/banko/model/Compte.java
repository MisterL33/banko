package banko.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Compte {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numero;
	private String libelle;
	private int solde;

	/**
	 * @return the solde
	 */
	public int getSolde() {
		return solde;
	}

	/**
	 * @param solde the solde to set
	 */
	public void setSolde(int solde) {
		this.solde = solde;
	}

	@OneToMany(mappedBy = "compte_recepteur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private transient List<Transaction> recepteur;
	
	@OneToMany(mappedBy = "compte_emetteur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private transient List<Transaction> emetteur;

	@ManyToOne
	private Client client;

	public String toString() {
		return numero + " " + libelle;
	}

	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
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
	 * @return the recepteur
	 */
	public List<Transaction> getRecepteur() {
		return recepteur;
	}

	/**
	 * @param recepteur the recepteur to set
	 */
	public void setRecepteur(List<Transaction> recepteur) {
		this.recepteur = recepteur;
	}

	/**
	 * @return the emetteur
	 */
	public List<Transaction> getEmetteur() {
		return emetteur;
	}

	/**
	 * @param emetteur the emetteur to set
	 */
	public void setEmetteur(List<Transaction> emetteur) {
		this.emetteur = emetteur;
	}

	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}


}
