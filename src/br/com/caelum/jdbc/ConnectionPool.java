package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hsqldb.jdbc.JDBCPool;

public class ConnectionPool {

	private DataSource dataSource;

	public ConnectionPool() {
		JDBCPool pool = new JDBCPool();
		pool.setUrl("jdbc:hsqldb:hsql://localhost/bdLojaVirtual");
		pool.setUser("SA");
		pool.setPassword("");
		this.dataSource = pool;
	}

	public Connection getConnection() throws SQLException {
		// Connection conexao =
		// DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/bdLojaVirtual",
		// "SA", "");
		Connection conexao = dataSource.getConnection();
		return conexao;
	}

}
