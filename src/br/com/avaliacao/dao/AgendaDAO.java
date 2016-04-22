package br.com.avaliacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.avaliacao.bean.Contato;
import br.com.avaliacao.bean.Usuario;

public class AgendaDAO {
	
	private final String INSERT = "insert into contato (nome,telefone,email,facebook,twitter,observacoes,usuario_id) values (?,?,?,?,?,?,?)";
	private final String SELECT = "select * from contato where usuario_id = ?";
	
	private final String CREATE = "CREATE TABLE `contato` (`id` int(11) NOT NULL AUTO_INCREMENT,`nome` varchar(45) NOT NULL,"
			+ "`telefone` varchar(45) NOT NULL,"
			+ "`email` varchar(45) NOT NULL,"
			+ "`facebook` varchar(45) NOT NULL,"
			+ "`twitter` varchar(45) NOT NULL,"
			+ "`observacoes` varchar(45) NOT NULL,"
			+ "`usuario_id` int(11) DEFAULT NULL,"
			+ "PRIMARY KEY (`id`),"
			+ "UNIQUE KEY `idContato_UNIQUE` (`id`),"
			+ "KEY `fk_usuario_idx` (`usuario_id`),"
			+ "CONSTRAINT `fk_usuario` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION) ";

	public List<Contato> getlistaDoUsuario(Usuario usuario) throws Exception {
		Connection conn 			= null;
        PreparedStatement stmt 	= null;
        ResultSet rs 				= null;
        List<Contato> lstContatos = new ArrayList<Contato>();
        
        try {
        	
        	conn = ConnectionManager.getConexao();
            stmt = conn.prepareStatement(SELECT);
            stmt.setInt(1, usuario.getId());
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                String nome 		= rs.getString("nome");
                String telefone 	= rs.getString("telefone");
                String email 		= rs.getString("email");
                String facebook 	= rs.getString("facebook");
                String twitter 		= rs.getString("twitter");
                String observacoes = rs.getString("observacoes");
                Contato contato 	= new Contato(nome, telefone, email, facebook, twitter, observacoes);
                lstContatos.add(contato);
            }
            
            return lstContatos;
			
		} catch (Exception e) {
			throw(e);
		}finally {
            ConnectionManager.closeAll(conn, stmt, rs);
        }
		
	}

	public void cadastrar(Contato c, Usuario u) throws SQLException {
		Connection conn 			= null;
        ResultSet rs 				= null;
        PreparedStatement pstm 	= null;
        try {
            conn = ConnectionManager.getConexao();
            pstm = conn.prepareStatement(INSERT);
            pstm.setString(1, c.getNome());
            pstm.setString(2, c.getTelefone());
            pstm.setString(3, c.getEmail());
            pstm.setString(4, c.getFacebook());
            pstm.setString(5, c.getTwitter());
            pstm.setString(6, c.getObservacoes());
            pstm.setInt(7, u.getId());
            pstm.execute();
        } catch (SQLException e) {
           throw(e);
        } finally {
            ConnectionManager.closeAll(conn, pstm, rs);
        }
	}

	public void createTableContato() throws Exception {
		Connection conn 			= null;
        PreparedStatement stmt 	= null;
        
        try {
        	
        	conn = ConnectionManager.getConexao();
            stmt = conn.prepareStatement(CREATE);
            stmt.execute();
			
		} catch (Exception e) {
			throw(e);
		}finally {
            ConnectionManager.closeAll(conn, stmt, null);
        }
		
	}
	
}
