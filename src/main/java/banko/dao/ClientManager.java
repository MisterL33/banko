package banko.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import banko.model.Client;
import banko.model.Transaction;

public class ClientManager {

	private static EntityManagerFactory emf = null;

	public static EntityManagerFactory getEmfInstance() {

		if (emf == null) {

			emf = Persistence.createEntityManagerFactory("banko");
		}

		return emf;
	}

	public static Client loadClientByID(int clientID) {

		Client result = new Client();

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/banque", "root", "");

			PreparedStatement stmt = con
					.prepareStatement("SELECT clientID, nom, prenom, login, passwd FROM client WHERE clientID=?");
			stmt.setInt(1, clientID);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				result.setClientID(rs.getInt("clientID"));
				result.setNom(rs.getString("nom"));
				result.setLogin(rs.getString("login"));
				result.setPrenom(rs.getString("prenom"));
				result.setPasswd(rs.getString("passwd"));
				System.out.println("Trouvé un client:" + result.toString());
			}
			rs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public static List<Client> getAllClient() {

		EntityManager em = getEmfInstance().createEntityManager();

		TypedQuery<Client> tQuery = em.createQuery("from Client", Client.class);
		List<Client> clientList = tQuery.getResultList();

		em.close();
		return clientList;
	}

	public static String getOneClientById(int id) {

		EntityManager em = getEmfInstance().createEntityManager();

		Client tQuery = em.find(Client.class, id);
		String client = tQuery.toString();
		em.close();
		return client;
	}

	public static Client getObjectClientById(int id) {

		EntityManager em = getEmfInstance().createEntityManager();

		TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.clientID = :id", Client.class);
		query.setParameter("id", id);

		try {
			Client c = query.getSingleResult();
			System.out.println("Recup client by id reussi");

			return c;

		} catch (NoResultException e) {
			System.out.println("erreur de recup by client id");

			return null;
		} finally {
			em.close();
		}

	}

	public static List<Transaction> getAllTransac() {

		EntityManager em = getEmfInstance().createEntityManager();

		TypedQuery<Transaction> tQuery = em.createQuery("from Transaction", Transaction.class);
		List<Transaction> transactionList = tQuery.getResultList();
		em.close();
		return transactionList;
	}

	public static Client CheckLogin(String login, String password) {

		EntityManager em = getEmfInstance().createEntityManager();

		TypedQuery<Client> query = em
				.createQuery("SELECT c FROM Client c WHERE c.login = :login AND c.passwd = :password", Client.class);
		query.setParameter("login", login);
		query.setParameter("password", password);
		try {
			Client c = query.getSingleResult();
			System.out.println("Connexion réussie");
			return c;
		} catch (NoResultException e) {
			System.out.println("erreur de connexion");
			return null;
		} finally {
			em.close();
		}

	}

}