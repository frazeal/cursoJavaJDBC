package br.com.caelum.testes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestaConexao {
	public static void main(String[] args) throws SQLException {
		Connection conexao = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/bdLojaVirtual", "SA", "");
		System.out.println("Conexão aberta com sucesso");
		conexao.close();
	}
}


