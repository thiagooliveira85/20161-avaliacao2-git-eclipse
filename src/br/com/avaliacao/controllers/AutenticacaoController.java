package br.com.avaliacao.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.avaliacao.bean.Usuario;
import br.com.avaliacao.business.AutenticaoBusiness;
import br.com.avaliacao.exception.BusinessException;

@WebServlet(urlPatterns="/AutenticacaoController")
public class AutenticacaoController extends HttpServlet {

	private static final String EFETUAR_LOGIN 			= "efetuarLogin";
	private static final String MOSTRAR_FORMULARIO = "mostrarFormulario";
	private static final String EFETUAR_LOGOUT 		= "efetuarLogout";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String acao = req.getParameter("acao");
			
			if (MOSTRAR_FORMULARIO.equals(acao)){
				mostrarFormularioLogin(req, resp);
			
			}else if(EFETUAR_LOGIN.equals(acao)){
				efetuarLogin(req, resp);
			}else if(EFETUAR_LOGOUT.equals(acao)){
				efetuarLogout(req, resp);
			}
			
		}catch (BusinessException e) {
			req.setAttribute("msgErro", e.getMessage());
			mostrarFormularioLogin(req, resp);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void mostrarFormularioLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("paginas/login.jsp").forward(req, resp);
	}

	private void efetuarLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String nome 		= req.getParameter("login");
		String senha 		= req.getParameter("senha");
		
		validaDadosDeEntrada(nome, senha);
		
		Usuario usuario 	= new AutenticaoBusiness().getUsuario(nome, senha);
		
		req.getSession().setAttribute("usuario", usuario);
		resp.sendRedirect("AgendaController?acao=listarContatos");
	}

	private void validaDadosDeEntrada(String nome, String senha) throws BusinessException {
		if ("".equals(nome))
			throw new BusinessException("Necessário informar o login!");
		
		if ("".equals(senha))
			throw new BusinessException("Necessário informar a senha!");
	}
	
	private void efetuarLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.getSession().invalidate();
		resp.sendRedirect("inicio");
	}

}
