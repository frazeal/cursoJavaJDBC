package br.com.caelum.testes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.caelum.jdbc.ConnectionPool;

public class TestaConexao {
	public static void main(String[] args) throws SQLException {
		Connection conexao = new ConnectionPool().getConnection();
		System.out.println("Conexão aberta com sucesso");
		conexao.close();
	}
}


