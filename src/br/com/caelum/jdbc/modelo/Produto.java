package br.com.caelum.jdbc.modelo;

public class Produto {
	private int id;
	private final String nome;
	private final String descricao;

	public Produto(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return String.format("[produto: %d %s %s]", this.id, this.nome, this.descricao);
	}
}
