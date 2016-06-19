package br.com.avaliacao.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.avaliacao.bean.Usuario;
import br.com.avaliacao.util.Util;

@WebServlet(urlPatterns="/inicio")
public class WelcomeController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (existeUsuarioLogado(req)){
			resp.sendRedirect("AgendaController?acao=listarContatos");
			return;
		}
		
		req.getRequestDispatcher("paginas/welcome.jsp").forward(req, resp);
		
	}

	private boolean existeUsuarioLogado(HttpServletRequest req) {
		Usuario usuario = Util.getUsuarioSession(req);
		if (usuario != null)
			return true;
		return false;
	}

}