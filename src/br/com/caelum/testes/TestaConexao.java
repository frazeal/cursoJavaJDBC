package br.com.caelum.testes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.caelum.jdbc.DataBase;

public class TestaConexao {
	public static void main(String[] args) throws SQLException {
		Connection conexao = DataBase.getConnection();
		System.out.println("Conexão aberta com sucesso");
		conexao.close();
	}
}


