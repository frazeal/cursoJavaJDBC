package br.com.caelum.jdbc.modelo;

public class CategoriaComProduto {
	
	private final Produto produto;
	private final Categoria categoria;

	public CategoriaComProduto(Categoria c, Produto p) {
		this.categoria = c;
		this.produto = p;
	}
	
	@Override
	public String toString() {
		return String.format("Categoria: %s | Produto: %s", this.categoria, this.produto);
	}
}
