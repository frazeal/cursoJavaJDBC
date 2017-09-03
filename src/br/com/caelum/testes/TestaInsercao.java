package br.com.caelum.testes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.caelum.jdbc.DataBase;

public class TestaInsercao {
	public static void main(String[] args) throws SQLException {
		try (Connection conexao = DataBase.getConnection()) {
			Statement sqlQuery = conexao.createStatement();
			boolean resultado = sqlQuery.execute(
					"INSERT INTO PRODUTOS (NOME, DESCRICAO) VALUES ('Notebook', 'Notebook i5')",
					sqlQuery.RETURN_GENERATED_KEYS);
			System.out.println(resultado);

			ResultSet registros = sqlQuery.getGeneratedKeys();
			while (registros.next()) {
				int id = registros.getInt("id");
				System.out.println(id + " gerado");
			}

			registros.close();
			sqlQuery.close();
		}
	}
}
