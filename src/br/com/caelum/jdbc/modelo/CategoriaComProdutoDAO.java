package br.com.caelum.jdbc.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaComProdutoDAO {

	private Connection conexao;

	public CategoriaComProdutoDAO(Connection c) {
		this.conexao = c;
	}

	public List<CategoriaComProduto> listar() throws SQLException {

		List<CategoriaComProduto> listaProdutos = new ArrayList<CategoriaComProduto>();
		String sqlQuery = "SELECT " + 
				"C.ID AS ID, " + 
				"C.NOME AS NOME, " + 
				"P.NOME AS NOME_PRODUTO, " + 
				"P.DESCRICAO AS DESC_PRODUTO, " + 
				"P.ID AS ID_PRODUTO " + 
				"FROM CATEGORIAS AS C " + 
				"INNER JOIN PRODUTOS AS P " + 
				"    ON P.CATEGORIA_ID = C.ID";
		
		try (PreparedStatement stmt = this.conexao.prepareStatement(sqlQuery)) {

			stmt.execute();

			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					Categoria c = new Categoria(rs.getInt("ID"), rs.getString("NOME"));
					Produto p = new Produto(rs.getString("NOME_PRODUTO"), rs.getString("DESC_PRODUTO"));
					p.setId(rs.getInt("ID_PRODUTO"));
					CategoriaComProduto cp = new CategoriaComProduto(c, p);
					listaProdutos.add(cp);
				}
			}

		}

		return listaProdutos;
	}
	
}
