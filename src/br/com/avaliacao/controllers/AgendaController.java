package br.com.avaliacao.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.avaliacao.bean.Contato;
import br.com.avaliacao.bean.Usuario;
import br.com.avaliacao.business.AgendaBusiness;
import br.com.avaliacao.util.Util;

@WebServlet(urlPatterns="/AgendaController")
public class AgendaController extends HttpServlet {

	private static final String LISTAR_CONTATOS 	= "listarContatos";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String acao = req.getParameter("acao");
			
			if (LISTAR_CONTATOS.equals(acao)){
				listarContatos(req,resp);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void listarContatos(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		
		Usuario usuario = Util.getUsuarioSession(req);
		
		if(usuario == null){
			resp.sendRedirect("inicio");
			return;
		}
			
		List<Contato> contatos = new AgendaBusiness().getlistaDoUsuario(usuario);
		req.setAttribute("contatos", contatos);		
		req.setAttribute("nomeUsuario", usuario.getNome());
		
		req.getRequestDispatcher("paginas/listaContatos.jsp").forward(req, resp);
		
	}

}
