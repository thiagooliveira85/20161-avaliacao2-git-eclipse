package br.com.avaliacao.util;

import static br.com.avaliacao.util.TagsHTML.BODY;
import static br.com.avaliacao.util.TagsHTML.BR;
import static br.com.avaliacao.util.TagsHTML.HEAD;
import static br.com.avaliacao.util.TagsHTML.HTML;
import static br.com.avaliacao.util.TagsHTML._HEAD;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import br.com.avaliacao.bean.Usuario;

public class Util {
	
	public static void criarCabecalho(PrintWriter writer){
		writer.println(HTML);
		writer.println(HEAD);
		writer.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>");
		writer.println("<title>Avaliação P1</title>");
		writer.println(_HEAD);
		writer.println(BODY);
		writer.println(BR);
		writer.println(BR);
		writer.println(BR);
		writer.println("<h1 align='center'><a href='inicio'>Desenvolvimento Web (Programação III)</a></h1>");
	}
	
	public static Usuario getUsuarioSession(HttpServletRequest req){
		try {
			return (Usuario)req.getSession().getAttribute("usuario");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
