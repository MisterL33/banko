package banko.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banko.dao.CompteManager;
import banko.dao.TransactionManager;
import banko.model.Compte;
import banko.model.perfectTransac;

/**
 * Servlet implementation class CompteServlet
 */
@WebServlet("/compte")
public class CompteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompteServlet() {
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

		if (request.getParameterMap().containsKey("id")) {
			String url = request.getParameter("id");
			int id = Integer.parseInt(url);

			int rowPerPage = 2;
			int actualLastResult = 0;
			Compte compte = CompteManager.getCompteByNumero(id);
			request.setAttribute("numeroCompte", compte.getNumero());
			request.setAttribute("libelleCompte", compte.getLibelle());

			if (request.getParameterMap().containsKey("page")) {
				String urlPage = request.getParameter("page");
				int page = Integer.parseInt(urlPage);
				if (page == 0) {
					actualLastResult = page;
				} else {
					actualLastResult = (page - 1) * rowPerPage;
				}

				perfectTransac transacPaginate = TransactionManager.getTransacByAccount(id, actualLastResult,
						rowPerPage);
				int nbPage = (transacPaginate.getTaille()) / 2;
				request.setAttribute("transacPaginate", transacPaginate.getTransaction());
				request.setAttribute("nbPage", nbPage);
			}
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/compte.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
