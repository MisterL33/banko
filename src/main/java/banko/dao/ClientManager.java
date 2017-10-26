package banko.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;
import javax.persistence.TypedQuery;

import banko.model.Client;
import banko.model.Compte;
import banko.model.Transaction;

public class ClientManager {
	
	public static Client loadClientByID(int clientID) {
	
	Client result = new Client();
	
	try {
		Connection con = DriverManager
				.getConnection("jdbc:mysql://localhost/banque","root","");
		
		PreparedStatement stmt = con.prepareStatement("SELECT clientID, nom, prenom, login, passwd FROM client WHERE clientID=?");
		stmt.setInt(1, clientID);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			result.setClientID(rs.getInt("clientID"));
			result.setNom(rs.getString("nom"));
			result.setLogin(rs.getString("login"));
			result.setPrenom(rs.getString("prenom"));
			result.setPasswd(rs.getString("passwd"));
			System.out.println("Trouvé un client:"+result.toString());
		}
		rs.close();
		con.close();
	}catch (Exception e) {
		e.printStackTrace();
	}
	return result;
		
	

	}
	
	public static List<Client> getAllClient() {
		
	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("banko");
		EntityManager em = emf.createEntityManager();

		TypedQuery<Client> tQuery = em.createQuery("from Client", Client.class);
		List<Client> clientList = tQuery.getResultList();
			
		
		return clientList;
	}
	
	public static String getOneClientById(int id) {
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("banko");
		EntityManager em = emf.createEntityManager();
		
	    Client tQuery = em.find(Client.class, id);
		String client = tQuery.toString();
		
		
		return client;
	}
	
	public static List<Compte> getAllCompte() {
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("banko");
		EntityManager em = emf.createEntityManager();

		TypedQuery<Compte> tQuery = em.createQuery("from Compte", Compte.class);
		List<Compte> compteList = tQuery.getResultList();
			
		
		return compteList;
	}
	
	
public static List<Transaction> getAllTransacFromCompte() {
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("banko");
		EntityManager em = emf.createEntityManager();

		TypedQuery<Transaction> tQuery = em.createQuery("from Transaction", Transaction.class);
		List<Transaction> transactionList = tQuery.getResultList();
			
		
		return transactionList;
	}
	
public static Client CheckLogin(String login, String password) {
	
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("banko");
	EntityManager em = emf.createEntityManager();
	
	 TypedQuery<Client> query = em.createQuery("SELECT c FROM Client c WHERE c.login = :login AND c.passwd = :password", Client.class);
	    query.setParameter("login", login);
	    query.setParameter("password", password); 
	    try{ 
	    Client c = query.getSingleResult();
	    System.out.println("Connexion réussie");
		return  c;
	    }
	    catch (NoResultException e){
	    	  System.out.println("erreur de connexion");
	    	  return null;
	    }
		
}


	
	
}