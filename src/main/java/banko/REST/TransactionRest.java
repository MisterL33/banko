package banko.REST;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banko.dao.CompteManager;
import banko.model.Compte;
import banko.model.Transaction;

/**
 * Servlet implementation class TransactionRest
 */
@WebServlet("/rest/TransactionRest")
public class TransactionRest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransactionRest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.print("hello");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("banko");
		EntityManager em = emf.createEntityManager();
		String libelle = request.getParameter("libelle");
		int montant = Integer.parseInt(request.getParameter("montant"));
		int idCompteEmetteur = Integer.parseInt(request.getParameter("compte_emetteur"));
		int idCompteRecepteur = Integer.parseInt(request.getParameter("compte_recepteur"));

		Compte compteEmetteur = CompteManager.getCompteByNumero(idCompteEmetteur);
		Compte compteRecepteur = CompteManager.getCompteByNumero(idCompteRecepteur);

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		int soldeEmetteur = compteEmetteur.getSolde();
		int soldeEmetteurAfter = soldeEmetteur - montant;
		int soldeRecepteur = compteRecepteur.getSolde();
		int soldeRecepteurAfter = soldeRecepteur + montant;
		if (soldeEmetteur > montant) {

			Transaction transaction = new Transaction();

			transaction.setCompte_emetteur(compteEmetteur);
			transaction.setCompte_recepteur(compteRecepteur);
			transaction.setDate(dateFormat.format(date));
			transaction.setLibelle(libelle);
			transaction.setMontant(montant);

			compteRecepteur.setSolde(soldeRecepteurAfter);
			compteEmetteur.setSolde(soldeEmetteurAfter);

			em.getTransaction().begin();
			Compte compte = em.merge(compteRecepteur);
			compte = em.merge(compteEmetteur);
			em.persist(compte);
			em.persist(transaction);

			em.getTransaction().commit();

			System.out.print("OK gros");
		}

	}
}
