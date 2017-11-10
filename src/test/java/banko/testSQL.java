package banko;

public class testSQL {

	// private static Logger logger = LogManager.getLogger(testSQL.class);
	//
	// @Test
	// public void testBase() {
	// EntityManagerFactory emf = Persistence.createEntityManagerFactory("banko");
	// EntityManager em = emf.createEntityManager();
	// Compte compte_emetteur = new Compte();
	// Compte compte_recepteur = new Compte();
	// Client client = new Client();
	// Transaction transaction = new Transaction();
	// client.setLogin("paul");
	// client.setNom("paul");
	// client.setPasswd("paul");
	// client.setPrenom("jambon");

	// compte.setLibelle("bim");

	// client.setLogin("astro");
	// client.setNom("astro");
	// client.setPasswd("astro");
	// client.setPrenom("rock");
	//
	// compte_emetteur.setLibelle("deuxieme compte astro");
	// compte_recepteur.setNumero(2);
	// transaction.setLibelle("lol");
	// transaction.setMontant(260);
	//
	// Compte compte = new Compte();
	// Client client = ClientManager.getOneClientById(2);
	// compte.setClient(client);

	//
	// Date date = new Date(0);
	// transaction.setDate("2017/10/2017");
	// em.getTransaction().begin();
	// client = em.merge(client);
	// em.persist(client);
	// compte_emetteur.setClient(client);
	// compte_emetteur = em.merge(compte_emetteur);
	// em.persist(compte_emetteur);
	// transaction = em.merge(transaction);
	// em.persist(transaction);
	// transaction.setCompte_emetteur(compte_emetteur);
	// transaction.setCompte_recepteur(compte_recepteur);
	//
	////
	// em.getTransaction().commit();
	//

	//
	// TypedQuery<Client> tQuery = em.createQuery("from Client", Client.class);
	// List<Client> clientList = tQuery.getResultList();
	//
	// PersistenceUtil util = Persistence.getPersistenceUtil();
	//
	// for(Client c : clientList) {

	// logger.info("Creation du client :" + client);
	// logger.info("Creation du compte associé :" + compte_emetteur);
	// logger.info("Creation d'une transaction :" + transaction);
	//
	// logger.info("#####");
	// logger.info(c.toString());
	// logger.debug("is client loaded ? "+util.isLoaded(c));
	// logger.debug("is compte loaded ? "+util.isLoaded(c.getComptes()));

	//
	// }
	// }

}
