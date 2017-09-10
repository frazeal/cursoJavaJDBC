package br.com.caelum.jdbc.modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.caelum.jdbc.ConnectionPool;

public class TestaProduto {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Produto mesa = new Produto("Mesa azul", "Mesa com 4 pés.");

		try (Connection conexao = new ConnectionPool().getConnection()) {
			ProdutosDAO dao = new ProdutosDAO(conexao);
			dao.inserir(mesa);

			List<Produto> produtos = dao.listar();
			imprimirListaDeProdutos(produtos);

		}

	}

	private static void imprimirListaDeProdutos(List<Produto> produtos) {
		for (Produto p : produtos) {
			System.out.println("Existe o produto: " + p);
		}
	}

}
