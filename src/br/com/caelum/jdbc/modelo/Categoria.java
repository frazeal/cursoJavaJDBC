package br.com.caelum.jdbc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

	private final String nome;
	private final int id;
	private final List<Produto> produtos;
	
	public Categoria(int id, String nome) {
		this.id = id;
		this.nome = nome;
		this.produtos = new ArrayList<Produto>();
	}
	
	public String getNome() {
		return nome;
	}
		
	public int getId() {
		return id;
	}
	
	public void adicionarProduto(Produto p) {
		this.produtos.add(p);
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	
	@Override
	public String toString() {
		return String.format("Categoria: %d - %s", this.id, this.nome);
	}
}
