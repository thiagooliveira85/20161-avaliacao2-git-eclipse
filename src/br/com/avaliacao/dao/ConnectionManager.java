package br.com.avaliacao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {

    private static final String STR_DRIVER 		= "com.mysql.jdbc.Driver";
    private static final String DATABASE 		= "despforca";
    private static final String IP 					= "localhost";
    private static final String STR_CON 			= "jdbc:mysql://" + IP + ":3306/" + DATABASE;
    private static final String USER 				= "root";
    private static final String PASSWORD 		= "root";

    public static Connection getConexao() {
        try {
            Class.forName(STR_DRIVER);
            return DriverManager.getConnection(STR_CON, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
		return null;
    }

    public static void closeAll(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeAll(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (conn != null || stmt != null) {
                closeAll(conn, stmt);
            }
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeAll(Connection conn, Statement stmt) {
        try {
            if (conn != null) {
                closeAll(conn);
            }
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

