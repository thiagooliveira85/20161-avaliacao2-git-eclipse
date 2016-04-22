package br.com.avaliacao.dao;

import java.util.Arrays;
import java.util.List;

import br.com.avaliacao.bean.Contato;
import br.com.avaliacao.bean.Usuario;

public class CreateTable {
	
	public static void main(String[] args) {
		
		try {
			
			UsuarioDao usuarioDao = new UsuarioDao();
			usuarioDao.createTableUsuario();
			usuarioDao.cadastrar(new Usuario("Thiago", "123"));
			usuarioDao.cadastrar(new Usuario("Bruno", "123"));
			
			AgendaDAO agendaDAO = new AgendaDAO();
			agendaDAO.createTableContato();
			
			Contato c1 = new Contato("Thiago Oliveira", "21976088108", "thiago@email.com", "https://www.facebook.com/gil.oliveira.73", "www.twitter.com", "Eu");
			Contato c2 = new Contato("Ton Brito", "21911546598", "Ton@gmail.com", "https://www.facebook.com/tondrummer", "www.twitter.com", "Irmão");
			Contato c3 = new Contato("Gil Oliveira", "21944551875", "kinha@yahoo.com", "https://www.facebook.com/gil.oliveira.73", "www.twitter.com", "Esposa");
			Contato c4 = new Contato("Alaide Cerqueira", "21976994522", "lala@email.com", "https://www.facebook.com/lala.73", "www.twitter.com", "Mãe");
			List<Contato> contatos = Arrays.asList(c1,c2,c3,c4);
			
			Usuario teste1 = new Usuario(1, "teste1", "123");
			
			for (Contato contato : contatos) {
				agendaDAO.cadastrar(contato, teste1);
			}
			
			Contato c5 = new Contato("Bruno Penha", "21922645578", "bruno.penha@email.com", "https://www.facebook.com/bruno.73", "www.twitter.com", "Professor");
			Contato c6 = new Contato("Pablo Guimarães", "21992884456", "pagvguerra@email.com", "https://www.facebook.com/pablo", "www.twitter.com", "Amigo");
			List<Contato> contatos2 = Arrays.asList(c5,c6);
			
			Usuario teste2 = new Usuario(2, "teste2", "123");
			
			for (Contato contato2 : contatos2) {
				agendaDAO.cadastrar(contato2, teste2);
			}
			
			System.out.println("SCRIPT EXECUTADO COM SUCESSO!");
			
		} catch (Exception e) {
			System.out.println("Erro na execução dos scripts");
			e.printStackTrace();
		}
		
	}

}
