package banko.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import banko.model.Transaction;
import banko.model.perfectTransac;

public class TransactionManager {

	private static EntityManagerFactory emf = null;

	public static EntityManagerFactory getEmfInstance() {

		if (emf == null) {

			emf = Persistence.createEntityManagerFactory("banko");
		}

		return emf;
	}

	public static perfectTransac getTransacByAccount(int numero, int actualLastResult, int rowPerPage) {

		EntityManager em = getEmfInstance().createEntityManager();

		TypedQuery<Transaction> tQuery = em.createQuery(
				"SELECT t FROM Transaction t WHERE t.compte_emetteur.numero = :numero OR t.compte_recepteur.numero = :numero ",
				Transaction.class);
		tQuery.setParameter("numero", numero);
		System.out.println("taille");
		int taille = tQuery.getResultList().size();

		try {
			List<Transaction> transactionList = tQuery.setMaxResults(rowPerPage).setFirstResult(actualLastResult)
					.getResultList();
			perfectTransac pageable = new perfectTransac();
			pageable.setTransaction(transactionList);
			pageable.setTaille(taille);

			System.out.println("recuperation transac réussi");

			return pageable;
		} catch (NoResultException e) {
			System.out.println("erreur de recuperation des transac");
			return null;
		} finally {
			em.close();
		}
	}

	public static List<Transaction> getDebitByAccount(int numero) {

		EntityManager em = getEmfInstance().createEntityManager();

		TypedQuery<Transaction> tQuery = em
				.createQuery("SELECT t FROM Transaction t WHERE t.compte_emetteur.numero = :numero", Transaction.class);
		tQuery.setParameter("numero", numero);

		try {
			List<Transaction> transactionList = tQuery.getResultList();
			System.out.println("recuperation transac réussi");
			return transactionList;
		} catch (NoResultException e) {
			System.out.println("erreur de recuperation des transac");
			return null;
		} finally {
			em.close();
		}
	}

	public static List<Transaction> getCreditByAccount(int numero) {

		EntityManager em = getEmfInstance().createEntityManager();

		TypedQuery<Transaction> tQuery = em.createQuery(
				"SELECT t FROM Transaction t WHERE t.compte_recepteur.numero = :numero", Transaction.class);
		tQuery.setParameter("numero", numero);

		try {
			List<Transaction> transactionList = tQuery.getResultList();
			System.out.println("recuperation transac credit réussi");
			return transactionList;
		} catch (NoResultException e) {
			System.out.println("erreur de recuperation des transac");
			return null;
		} finally {
			em.close();
		}
	}

}
