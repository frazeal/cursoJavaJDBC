package br.com.caelum.jdbc.testes;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.caelum.jdbc.ConnectionPool;
import br.com.caelum.jdbc.modelo.Categoria;
import br.com.caelum.jdbc.modelo.CategoriasDAO;
import br.com.caelum.jdbc.modelo.Produto;

public class TestaCategorias {
	public static void main(String[] args) throws SQLException {
		
		try (Connection con = new ConnectionPool().getConnection()) {
			
			CategoriasDAO dao = new CategoriasDAO(con);
			List<Categoria> categorias = dao.listarComProdutos();
			for (Categoria c : categorias) {
				System.out.println(c);
				
				for (Produto p : c.getProdutos()) {
					System.out.println(p);
				}
			}
			/*List<CategoriaComProduto> listaProdutos = new CategoriaComProdutoDAO(con).listar();
			for (CategoriaComProduto cp : listaProdutos) {
				System.out.println(cp);
			}*/
		}
	}
}
