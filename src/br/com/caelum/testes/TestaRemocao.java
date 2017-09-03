package br.com.caelum.testes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.caelum.jdbc.ConnectionPool;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {

		try (Connection conexao = new ConnectionPool().getConnection()) {
			try (Statement sqlQuery = conexao.createStatement()) {
				boolean resultado = sqlQuery.execute("DELETE FROM PRODUTOS WHERE ID > 3");
				int alteracoes = sqlQuery.getUpdateCount();
				System.out.println(alteracoes + " linhas alteradas");
				System.out.println(resultado);
			}
		}

	}
}
