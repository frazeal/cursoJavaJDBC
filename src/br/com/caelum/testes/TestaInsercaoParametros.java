package br.com.caelum.testes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.caelum.jdbc.DataBase;

public class TestaInsercaoParametros {
	public static void main(String[] args) throws SQLException {
		try (Connection conexao = DataBase.getConnection()) {
			conexao.setAutoCommit(false);
			String sqlString = "INSERT INTO PRODUTOS (NOME, DESCRICAO) VALUES (?, ?)";

			try (PreparedStatement sqlQuery = conexao.prepareStatement(sqlString,
					Statement.RETURN_GENERATED_KEYS);) {

				adicionarRegistro("Notebook'i5", "Notebook i5", sqlQuery);
				adicionarRegistro("TV LCD", "32 polegadas", sqlQuery);
				adicionarRegistro("Blueray", "full hd", sqlQuery);
				conexao.commit();
				
			} catch (Exception e) {
				
				e.printStackTrace();
				conexao.rollback();
				System.out.println("Rollback efetuado");
				
			}
		}
	}

	private static void adicionarRegistro(String nome, String descricao, PreparedStatement sqlQuery)
			throws SQLException {

		if (nome.equals("Blueray")) {
			throw new IllegalArgumentException("Simulando erro em transação sql.");
		}

		sqlQuery.setString(1, nome);
		sqlQuery.setString(2, descricao);

		boolean resultado = sqlQuery.execute();
		System.out.println(resultado);

		try (ResultSet registros = sqlQuery.getGeneratedKeys()) {
			while (registros.next()) {
				int id = registros.getInt("id");
				System.out.println(id + " gerado");
			}
		}
		
	}
}
