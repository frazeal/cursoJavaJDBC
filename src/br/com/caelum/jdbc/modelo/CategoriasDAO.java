package br.com.caelum.jdbc.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriasDAO {

	private Connection conexao;

	public CategoriasDAO(Connection c) {
		this.conexao = c;
	}

	public List<Categoria> listar() throws SQLException {

		List<Categoria> categorias = new ArrayList<Categoria>();
		String sqlQuery = "SELECT * FROM CATEGORIAS";

		try (PreparedStatement stmt = this.conexao.prepareStatement(sqlQuery)) {

			stmt.execute();

			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					Categoria c = new Categoria(rs.getInt("id"), rs.getString("nome"));
					categorias.add(c);
				}
			}

		}

		return categorias;
	}

	public List<Categoria> listarComProdutos() throws SQLException {

		List<Categoria> categorias = new ArrayList<Categoria>();
		Categoria ultimaCategoria = null;

		String sqlQuery = "SELECT " + "C.ID AS ID, " + "C.NOME AS NOME, " + "P.NOME AS NOME_PRODUTO, "
				+ "P.DESCRICAO AS DESC_PRODUTO, " + "P.ID AS ID_PRODUTO " + "FROM CATEGORIAS AS C "
				+ "INNER JOIN PRODUTOS AS P " + "    ON P.CATEGORIA_ID = C.ID ORDERY BY ID";

		try (PreparedStatement stmt = this.conexao.prepareStatement(sqlQuery)) {

			stmt.execute();

			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					int idCategoria = rs.getInt("ID");
					if (ultimaCategoria == null || !(ultimaCategoria.getId() == idCategoria)) {
						Categoria c = new Categoria(rs.getInt("ID"), rs.getString("NOME"));
						categorias.add(c);
						ultimaCategoria = c;
					}

					Produto p = new Produto(rs.getString("NOME_PRODUTO"), rs.getString("DESC_PRODUTO"));
					p.setId(rs.getInt("ID_PRODUTO"));
					ultimaCategoria.adicionarProduto(p);
				}
			}

		}

		return categorias;
	}

}
