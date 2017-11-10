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

/**
 * Servlet implementation class PasswordModif
 */
@WebServlet("/reset")
public class PasswordModif extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PasswordModif() {
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

		this.getServletContext().getRequestDispatcher("/WEB-INF/PasswordModif.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		System.out.println("ici");

		EntityManager em = ClientManager.getEmfInstance().createEntityManager();
		HttpSession session = request.getSession(false);
		int clientID = (int) session.getAttribute("clientID");
		if (password.equals(password2)) {
			Client client = ClientManager.getObjectClientById(clientID);
			em.getTransaction().begin();
			client.setPasswd(password);
			client = em.merge(client);

			em.persist(client);
			em.getTransaction().commit();
			System.out.print(clientID);
			System.out.print("ok");
			request.setAttribute("validator", "Mot de passe changé");
			this.getServletContext().getRequestDispatcher("/WEB-INF/PasswordModif.jsp").forward(request, response);
		} else {
			request.setAttribute("validator", "Erreur : mot de passe non similaire");
			System.out.print("pas ok");
			this.getServletContext().getRequestDispatcher("/WEB-INF/PasswordModif.jsp").forward(request, response);
		}

	}

}
