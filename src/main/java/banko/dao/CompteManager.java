package banko.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import banko.model.Compte;

public class CompteManager {

	private static EntityManagerFactory emf = null;

	public static EntityManagerFactory getEmfInstance() {

		if (emf == null) {

			emf = Persistence.createEntityManagerFactory("banko");
		}

		return emf;
	}

	public static List<Compte> getAllCompte() {

		EntityManager em = getEmfInstance().createEntityManager();

		TypedQuery<Compte> tQuery = em.createQuery("from Compte", Compte.class);
		List<Compte> compteList = tQuery.getResultList();
		em.close();
		return compteList;
	}

	public static List<Compte> getClientAccount(int id) {

		EntityManager em = getEmfInstance().createEntityManager();

		TypedQuery<Compte> tQuery = em.createQuery("SELECT c FROM Compte c WHERE c.client.clientID = :clientID",
				Compte.class);
		tQuery.setParameter("clientID", id);

		try {
			List<Compte> compteList = tQuery.getResultList();
			System.out.println("recuperation réussi");
			return compteList;
		} catch (NoResultException e) {
			System.out.println("erreur de recuperation du compte");
			return null;
		} finally {
			em.close();
		}

	}

	public static Compte getCompteByNumero(int numero) {

		EntityManager em = getEmfInstance().createEntityManager();

		TypedQuery<Compte> query = em.createQuery("SELECT c FROM Compte c WHERE c.numero = :numero", Compte.class);
		query.setParameter("numero", numero);

		try {
			Compte c = query.getSingleResult();
			System.out.println("Recup compte by id reussi");
			return c;
		} catch (NoResultException e) {
			System.out.println("erreur de recup by compte id");
			return null;
		} finally {
			em.close();
		}

	}

	public static List<Compte> getAllReceptor() {

		EntityManager em = getEmfInstance().createEntityManager();

		TypedQuery<Compte> tQuery = em.createQuery("SELECT c FROM Compte c ", Compte.class);

		try {
			List<Compte> compteList = tQuery.getResultList();
			System.out.println("recuperation réussi des recepteurs");
			return compteList;
		} catch (NoResultException e) {
			System.out.println("erreur de recuperation du compte");
			return null;
		} finally {
			em.close();
		}

	}

}
