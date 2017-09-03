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
			String nome = "Microondas";
			String descricao = "Mircroondas branco de 20 litros.";

			String sqlString = "INSERT INTO PRODUTOS (NOME, DESCRICAO) VALUES (?, ?)";

			PreparedStatement sqlQuery = conexao.prepareStatement(sqlString, Statement.RETURN_GENERATED_KEYS);
			sqlQuery.setString(1, nome);
			sqlQuery.setString(2, descricao);

			boolean resultado = sqlQuery.execute();
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
