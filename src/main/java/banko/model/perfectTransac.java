package banko.model;

import java.util.List;

public class perfectTransac {

	private int taille;
	private List<Transaction> transaction;

	/**
	 * @return the taille
	 */
	public int getTaille() {
		return taille;
	}

	/**
	 * @param taille
	 *            the taille to set
	 */
	public void setTaille(int taille) {
		this.taille = taille;
	}

	/**
	 * @return the transaction
	 */
	public List<Transaction> getTransaction() {
		return transaction;
	}

	/**
	 * @param transaction
	 *            the transaction to set
	 */
	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

}
