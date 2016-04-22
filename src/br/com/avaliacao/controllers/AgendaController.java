package br.com.avaliacao.controllers;

import static br.com.avaliacao.util.TagsHTML.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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

	private static final String INCLUIR 				= "incluir";
	private static final String CADASTRAR 			= "cadastrar";
	private static final String LISTAR_CONTATOS 	= "listarContatos";
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
			
			if (LISTAR_CONTATOS.equals(acao)){
				listarContatos(writer, req,resp);
			
			}else if (INCLUIR.equals(acao)){
				mostrarFormularioInclusao(writer, req,resp);
			
			}else if (CADASTRAR.equals(acao)){
				cadastrar(writer, req,resp);
			}
			
		}catch (Exception e) {
			Util.criarCabecalho(writer);
			writer.println("<h1>" + e.getMessage() + "</h1>");
		}finally{
			writer.println(_BODY);
	        writer.println(_HTML);
			writer.close();
		}
		
	}

	private void cadastrar(PrintWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String nome 		= req.getParameter("nome");
		String telefone 	= req.getParameter("telefone");
		String email 		= req.getParameter("email");
		String facebook 	= req.getParameter("facebook");
		String twitter 		= req.getParameter("twitter");
		String obs 			= req.getParameter("obs");
		
		Contato contato = new Contato(nome, telefone, email, facebook, twitter, obs);
		new AgendaBusiness().cadastrar(contato, Util.getUsuarioSession(req));
		listarContatos(writer, req,resp);
	}

	private void mostrarFormularioInclusao(PrintWriter writer, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		exibeBoasVindas(writer, Util.getUsuarioSession(req), resp);
		writer.println("<form action='AgendaController?acao=cadastrar' method='POST'>");
		writer.println("<p><label>Nome:</label><input type='text' name='nome' /></p>");
		writer.println("<p><label>Telefone:</label><input type='text' name='telefone' /></p>");
		writer.println("<p><label>Email:</label><input type='text' name='email' /></p>");
		writer.println("<p><label>Facebook:</label><input type='text' name='facebook' /></p>");
		writer.println("<p><label>Twitter:</label><input type='text' name='twitter' /></p>");
		writer.println("<p><label>Observações:</label><input type='text' name='obs' /></p>");
		writer.println("<p><input type='submit' value='Cadastrar' /></p>");
		writer.println("</form>");
	}

	private void listarContatos(PrintWriter writer, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		
		Usuario usuario = Util.getUsuarioSession(req);
		
		exibeBoasVindas(writer, usuario, resp);
			
		List<Contato> contatos = new AgendaBusiness().getlistaDoUsuario(usuario);
		
		writer.print(BR);
		writer.print("<a href='AgendaController?acao=incluir'><img width='30' height='30' src='imagens/" + "novo2.png" + "' title='Incluir contato'/></a>");
		writer.print(BR);
		writer.print(BR);
		
		writer.print(TABLE);
		writer.print("<TR>");
		writer.print("<TH>Nome</TH>");
		writer.print("<TH>Telefone</TH>");
		writer.print("<TH>Email</TH>");
		writer.print("<TH>Facebook</TH>");
		writer.print("<TH>Twitter</TH>");
		writer.print("<TH>Observações</TH>");
		writer.print("<TH></TH>");
		writer.print("</TR>");
		
		for (Contato contato : contatos) {
			writer.print("<TR>");
			writer.print("<TD>" + contato.getNome() + "</TD>");
			writer.print("<TD>" + contato.getTelefone()+ "</TD>");
			writer.print("<TD><a href=mailto:'" + contato.getEmail() + "'>" + contato.getEmail() + "</a></TD>");
			writer.print("<TD><a href='" + contato.getFacebook() + "'>" + contato.getFacebook() + "</a></TD>");
			writer.print("<TD><a href='" + contato.getTwitter() + "'>" + contato.getTwitter() + "</a></TD>");
			writer.print("<TD>" + contato.getObservacoes() + "</TD>");
			writer.print("<TD><a href='#'><img width='30' height='30' src='imagens/" + "editar.png" + "' title='Em construção' /></a></TD>");
			writer.print("</TR>");
		}
		
		writer.print(_TABLE);
		
	}

	private void exibeBoasVindas(PrintWriter writer, Usuario usuario, HttpServletResponse resp) throws IOException {
		if(usuario == null){
			resp.sendRedirect("inicio");
			return;
		}
		writer.print("<h3>Seja bem vindo " + usuario.getNome() + " a sua agenda de contatos virtual! <a href='AutenticacaoController?acao=efetuarLogout'><img width='30' height='30' src='imagens/" + "logout.png" + "' title='Efetuar logoff'/></a></h3>");
	}

}
