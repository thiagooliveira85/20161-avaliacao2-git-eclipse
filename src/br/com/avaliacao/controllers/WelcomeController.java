package br.com.avaliacao.controllers;

import static br.com.avaliacao.util.TagsHTML.*;

import java.io.IOException;
import java.io.PrintWriter;

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
		
		PrintWriter writer = resp.getWriter();        
		resp.setContentType("text/html");
			
		Util.criarCabecalho(writer);
		
		if (existeUsuarioLogado(req)){
			resp.sendRedirect("AgendaController?acao=listarContatos");
			return;
		}
		
        writer.println("<h3>Seja bem vindo a agenda de contatos virtual</h3>");
        
        writer.println(BR);
        writer.println(BR);
        writer.println("<h4>Nela você poderá cadastrar e atualizar a sua lista de contatos das redes sociais, twitter, facebook, instagran e etc.</h4>");
        writer.println(BR);
        
        writer.println("Para acessar sua agenda é necessário realizar o login clicando <a href='AutenticacaoController?acao=mostrarFormulario'>aqui.</a>");
        
        writer.println(_BODY);
        writer.println(_HTML);
		writer.close();


	}

	private boolean existeUsuarioLogado(HttpServletRequest req) {
		Usuario usuario = Util.getUsuarioSession(req);
		if (usuario != null)
			return true;
		return false;
	}

}