package br.com.avaliacao.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.avaliacao.bean.Usuario;
import br.com.avaliacao.business.AutenticaoBusiness;
import br.com.avaliacao.exception.BusinessException;
import br.com.avaliacao.util.Util;
import static br.com.avaliacao.util.TagsHTML.*;

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
		
		PrintWriter writer = resp.getWriter();        
		resp.setContentType("text/html");
		Util.criarCabecalho(writer);
		
		try {
			
			String acao = req.getParameter("acao");
			
			if (MOSTRAR_FORMULARIO.equals(acao)){
				mostrarFormularioLogin(writer, req, resp);
			
			}else if(EFETUAR_LOGIN.equals(acao)){
				efetuarLogin(writer, req, resp);
			}else if(EFETUAR_LOGOUT.equals(acao)){
				efetuarLogout(writer, req, resp);
			}
			
		}catch (BusinessException e) {
			mostrarFormularioLogin(writer, req, resp);
			writer.println("<h3><font color='red'>" + e.getMessage() + "</font></h3>");
		}catch (Exception e) {
			writer.println("<h1>" + e.getMessage() + "</h1>");
		}finally{
			writer.println(_BODY);
	        writer.println(_HTML);
			writer.close();
		}
		
	}
	
	private void mostrarFormularioLogin(PrintWriter writer, HttpServletRequest req, HttpServletResponse resp) {
		
		writer.println("<form action='AutenticacaoController?acao=efetuarLogin' method='POST'>");
		writer.println("<p><label>Login:</label><input type='text' name='login' /></p>");
		writer.println("<p><label>Senha:</label><input type='password' name='senha' /></p>");
		writer.println("<p><input type='submit' value='Logar' /></p>");
		writer.println("</form>");
		
	}

	private void efetuarLogin(PrintWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
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
	
	private void efetuarLogout(PrintWriter writer, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.getSession().invalidate();
		resp.sendRedirect("inicio");
	}

}
