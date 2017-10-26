package banko;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import banko.model.Client;
import banko.model.Compte;
import banko.model.Transaction;

public class testSQL {
	
	private static Logger logger = LogManager.getLogger(testSQL.class);

	@Test
	public void testBase() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("banko");
		EntityManager em = emf.createEntityManager();
		Compte compte = new Compte();
		Client client = new Client();
		Transaction transaction = new Transaction();
		client.setLogin("salhaut");
		client.setNom("nomOriginal");		
		client.setPasswd("salhaut");
		client.setPrenom("tuc");
		
		compte.setLibelle("bim");

		
	
	
		transaction.setLibelle("transac");
		transaction.setMontant(500);
		
	
		Date date = new Date(0);
		transaction.setDate(date);
		em.getTransaction().begin();
		client = em.merge(client);
		em.persist(client);
		compte.setClient(client);
		compte = em.merge(compte);
		em.persist(compte);
		transaction = em.merge(transaction);
		em.persist(transaction);
		transaction.setCompte(compte);
		
		
		em.getTransaction().commit();
		
	
		
		TypedQuery<Client> tQuery = em.createQuery("from Client", Client.class);
		List<Client> clientList = tQuery.getResultList();
		
		PersistenceUtil util = Persistence.getPersistenceUtil();
		
		for(Client c : clientList) {
		
		
		logger.info("Creation du client :" + client);
		logger.info("Creation du compte associé :" + compte);
		logger.info("Creation d'une transaction :" + transaction);
		
		logger.info("#####");
		logger.info(c.toString());
		logger.debug("is client loaded ? "+util.isLoaded(c));
		logger.debug("is compte loaded ? "+util.isLoaded(c.getComptes()));
	
	
		}
	}
}

