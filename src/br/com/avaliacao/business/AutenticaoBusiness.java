package br.com.avaliacao.business;

import br.com.avaliacao.bean.Usuario;
import br.com.avaliacao.dao.UsuarioDao;
import br.com.avaliacao.exception.BusinessException;

public class AutenticaoBusiness {
	
	private UsuarioDao dao;
	
	public AutenticaoBusiness(){
		dao = new UsuarioDao();
	}

	public Usuario getUsuario(String nome, String senha) throws Exception {
		Usuario usuario = dao.getUsuario(nome, senha);
		if (usuario == null)
			throw new BusinessException("Login ou senha inválidos!");
		return usuario;
	}

}
