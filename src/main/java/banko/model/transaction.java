package banko.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int transacID;
	private String libelle;
	private Date date;
	private int  montant;
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "transaction [transacID=" + transacID + ", libelle=" + libelle + ", date=" + date + ", montant="
				+ montant + "]";
	}
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
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
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

}
