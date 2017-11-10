package banko.Servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import banko.dao.ClientManager;
import banko.dao.CompteManager;
import banko.model.Compte;
import banko.model.Transaction;

/**
 * Servlet implementation class VirementServlet
 */
@WebServlet("/virement")
public class VirementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VirementServlet() {
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
		HttpSession session = request.getSession(false);
		int id = (int) session.getAttribute("clientID");
		getListCompte(request, id);

		this.getServletContext().getRequestDispatcher("/WEB-INF/virement.jsp").forward(request, response);
	}

	private void getListCompte(HttpServletRequest request, int id) {

		List<Compte> emetteurs = CompteManager.getClientAccount(id);
		List<Compte> recepteurs = CompteManager.getAllReceptor();
		request.setAttribute("emetteurs", emetteurs);
		request.setAttribute("recepteurs", recepteurs);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		EntityManager em = ClientManager.getEmfInstance().createEntityManager();
		int montant = Integer.parseInt(request.getParameter("montantTransac"));
		Compte compteEmetteur = CompteManager.getCompteByNumero(Integer.parseInt(request.getParameter("idEmetteur")));

		Compte compteRecepteur = CompteManager.getCompteByNumero(Integer.parseInt(request.getParameter("idRecepteur")));
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
			transaction.setLibelle(request.getParameter("libelleTransac"));
			transaction.setMontant(montant);

			compteRecepteur.setSolde(soldeRecepteurAfter);
			compteEmetteur.setSolde(soldeEmetteurAfter);

			em.getTransaction().begin();
			Compte compte = em.merge(compteRecepteur);
			compte = em.merge(compteEmetteur);
			em.persist(compte);
			em.persist(transaction);

			em.getTransaction().commit();

			String path = request.getContextPath();
			response.sendRedirect(path + "/home");
		} else {
			HttpSession session = request.getSession(false);
			System.out.println("ok ta pas assez de flouz");
			getListCompte(request, (int) session.getAttribute("clientID"));
			request.setAttribute("virementBool", "Ta pas assez de flouz mec");

			this.getServletContext().getRequestDispatcher("/WEB-INF/virement.jsp").forward(request, response);
		}

	}

}
