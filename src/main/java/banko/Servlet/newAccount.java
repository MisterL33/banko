package banko.Servlet;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import banko.dao.ClientManager;
import banko.model.Client;
import banko.model.Compte;

/**
 * Servlet implementation class newAccount
 */
@WebServlet("/newAccount")
public class newAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public newAccount() {
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
		this.getServletContext().getRequestDispatcher("/WEB-INF/newAccount.jsp").forward(request, response);
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
		String libelle = request.getParameter("libelleCompte");
		HttpSession session = request.getSession(false);
		int clientID = (int) session.getAttribute("clientID");
		Client client = ClientManager.getObjectClientById(clientID);
		Compte compte = new Compte();
		em.getTransaction().begin();
		compte.setClient(client);
		compte.setLibelle(libelle);
		compte.setSolde(0);
		em.persist(compte);
		em.getTransaction().commit();
		this.getServletContext().getRequestDispatcher("/WEB-INF/accountHome.jsp").forward(request, response);
	}

}
