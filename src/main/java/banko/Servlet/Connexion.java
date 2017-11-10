package banko.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import banko.dao.ClientManager;
import banko.dao.CompteManager;
import banko.model.Client;
import banko.model.Compte;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/home")
public class Connexion extends HttpServlet {
	public static final String ATT_USER = "client";
	public static final String ATT_FORM = "form";
	public static final String ATT_SESSION_USER = "sessionClient";
	public static final String VUE = "/WEB-INF/home.jsp";
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Connexion() {
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
		HttpSession session = request.getSession();
		if (session.getAttribute("nom") == null) {

			this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
		} else {

			int id = (int) session.getAttribute("clientID");
			getListCompte(request, id);
			this.getServletContext().getRequestDispatcher("/WEB-INF/accountHome.jsp").forward(request, response);

		}

	}

	private void getListCompte(HttpServletRequest request, int id) {

		List<Compte> comptes = CompteManager.getClientAccount(id);
		List<Compte> recepteurs = CompteManager.getAllReceptor();
		request.setAttribute("comptes", comptes);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter("login");
		String password = request.getParameter("password");

		boolean success = false;
		String fail = "Identifiants incorrect";

		if (ClientManager.CheckLogin(login, password) != null) {
			HttpSession session = request.getSession();
			success = true;
			session.setAttribute("checker", success);
			Client client = new Client();

			client = ClientManager.CheckLogin(login, password);
			int clientID = client.getClientID();
			session.setAttribute("id", clientID);
			List<Compte> compte = CompteManager.getClientAccount(clientID);

			// int numeroCompte = compte.getNumero();
			// String libelleCompte = compte.getLibelle();
			String nom = client.getNom();
			String prenom = client.getPrenom();

			session.setAttribute("nom", nom);
			session.setAttribute("prenom", prenom);
			session.setAttribute("comptes", compte);
			session.setAttribute("clientID", clientID);
			// session.setAttribute("numeroCompte", numeroCompte);
			// session.setAttribute("libelleCompte", libelleCompte);
			// String path = request.getContextPath();
			// response.sendRedirect(path + "/compte");

			this.getServletContext().getRequestDispatcher("/WEB-INF/accountHome.jsp").forward(request, response);
		} else {
			request.setAttribute("checker", fail);
			request.getSession().invalidate();
			this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
		}

		System.out.println(login);
		System.out.println(password);

	}

}
