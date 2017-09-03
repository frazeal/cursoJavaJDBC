package br.com.caelum.testes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaListagem {
	public static void main(String[] args) throws SQLException {

		try (Connection conexao = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/bdLojaVirtual", "SA", "")) {
			Statement sqlQuery = conexao.createStatement();
			boolean resultado = sqlQuery.execute("SELECT * FROM PRODUTOS");
			ResultSet registros = sqlQuery.getResultSet();

			while (registros.next()) {
				int id = registros.getInt("id");
				String nome = registros.getString("nome");
				String descricao = registros.getString("descricao");
				System.out.println(id + " | " + nome + " | " + descricao);
			}
			registros.close();
			sqlQuery.close();
		} // conexao.close();
	}
}
