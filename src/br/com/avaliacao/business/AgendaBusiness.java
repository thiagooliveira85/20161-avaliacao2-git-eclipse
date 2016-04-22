package br.com.avaliacao.business;

import java.sql.SQLException;
import java.util.List;

import br.com.avaliacao.bean.Contato;
import br.com.avaliacao.bean.Usuario;
import br.com.avaliacao.dao.AgendaDAO;

public class AgendaBusiness {
	
	private AgendaDAO dao;
	
	public AgendaBusiness(){
		dao = new AgendaDAO();
	}

	public List<Contato> getlistaDoUsuario(Usuario usuario) throws Exception {
		return dao.getlistaDoUsuario(usuario);
	}

	public void cadastrar(Contato contato, Usuario usuario) throws SQLException {
		dao.cadastrar(contato, usuario);
	}
	
	

}
