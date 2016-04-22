package br.com.avaliacao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.avaliacao.bean.Contato;
import br.com.avaliacao.bean.Usuario;

public class UsuarioDao {
	
	private final String SELECT = "select * from usuario where upper(nome) = ? and senha = ?";
	private final String INSERT = "insert into usuario (nome,senha) values (?,?)";
	private final String CREATE = "CREATE TABLE `usuario` (`id` int(11) NOT NULL AUTO_INCREMENT,`nome` varchar(45) NOT NULL, "
			+ "`senha` varchar(45) NOT NULL,"
			+ "PRIMARY KEY (`id`),"
			+ "UNIQUE KEY `id_UNIQUE` (`id`)) ";

	public Usuario getUsuario(String nome, String senha) throws Exception {
		
		Connection conn 			= null;
        PreparedStatement stmt 	= null;
        ResultSet rs 				= null;
        
        try {
        	
        	conn = ConnectionManager.getConexao();
            stmt = conn.prepareStatement(SELECT);
            stmt.setString(1, nome.toUpperCase());
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            
            if (rs.next())
            	return new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("senha"));
            
            return null;
			
		} catch (Exception e) {
			throw(e);
		}finally {
            ConnectionManager.closeAll(conn, stmt, rs);
        }
	}
	
	public void createTableUsuario() throws Exception {
		
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

	public void cadastrar(Usuario usuario) throws SQLException {
		Connection conn 			= null;
        ResultSet rs 				= null;
        PreparedStatement pstm 	= null;
        try {
            conn = ConnectionManager.getConexao();
            pstm = conn.prepareStatement(INSERT);
            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getSenha());          
            pstm.execute();
            
        } catch (SQLException e) {
           throw(e);
        } finally {
            ConnectionManager.closeAll(conn, pstm, rs);
        }
	}

}
