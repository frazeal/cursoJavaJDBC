package br.com.caelum.jdbc.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

	private Connection conexao;

	public ProdutosDAO(Connection c) {
		this.conexao = c;
	}

	public void inserir(Produto produto) throws SQLException {

		String sql = "INSERT INTO PRODUTOS (NOME, DESCRICAO) VALUES (?,?)";

		try (PreparedStatement statement = this.conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			statement.setString(1, produto.getNome());
			statement.setString(2, produto.getDescricao());
			statement.execute();

			try (ResultSet rs = statement.getGeneratedKeys()) {
				if (rs.next()) {
					produto.setId(rs.getInt("id"));
					//System.out.println(produto.getId());
				}
			}

			this.conexao.commit();
		}
	}

	public List<Produto> listar() throws SQLException {

		List<Produto> produtos = new ArrayList<Produto>();
		String sqlQuery = "SELECT * FROM PRODUTOS";

		try (PreparedStatement stmt = this.conexao.prepareStatement(sqlQuery)) {

			stmt.execute();

			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					Produto produto = new Produto(rs.getString("nome"), rs.getString("descricao"));
					produto.setId(rs.getInt("id"));
					produtos.add(produto);
				}
			}
		}

		return produtos;
	}

	public Produto buscarPorId(int id) throws SQLException {
		Produto produto = null;

		String sqlQuery = "SELECT * FROM PRODUTOS WHERE ID = ?";

		try (PreparedStatement stmt = this.conexao.prepareStatement(sqlQuery)) {

			stmt.setLong(1, id);
			stmt.execute();

			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					produto = new Produto(rs.getString("nome"), rs.getString("descricao"));
					produto.setId(rs.getInt("id"));
				}
			}

		}

		return produto;
	}

	public void apagar(int id) throws SQLException {

		String sqlQuery = "DELETE FROM PRODUTOS WHERE ID = ?";

		try (PreparedStatement stmt = this.conexao.prepareStatement(sqlQuery)) {

			stmt.setLong(1, id);
			stmt.execute();
			this.conexao.commit();
		}

	}

	public void alterar(Produto p) throws SQLException {

		String sqlQuery = "UPDATE PRODUTOS SET NOME = ?, DESCRICAO = ? WHERE ID = ?";

		try (PreparedStatement stmt = this.conexao.prepareStatement(sqlQuery)) {

			stmt.setString(1, p.getNome());
			stmt.setString(2, p.getDescricao());
			stmt.setLong(3, p.getId());
			stmt.execute();
			this.conexao.commit();

		}
	}
}
