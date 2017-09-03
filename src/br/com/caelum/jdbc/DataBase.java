package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {

	public static Connection getConnection() throws SQLException {
		Connection conexao = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/bdLojaVirtual", "SA", "");
		return conexao;
	}

	
}
