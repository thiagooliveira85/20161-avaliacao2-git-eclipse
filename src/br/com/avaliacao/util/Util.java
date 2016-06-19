package br.com.avaliacao.util;

import javax.servlet.http.HttpServletRequest;

import br.com.avaliacao.bean.Usuario;

public class Util {
	
	public static Usuario getUsuarioSession(HttpServletRequest req){
		try {
			return (Usuario)req.getSession().getAttribute("usuario");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
