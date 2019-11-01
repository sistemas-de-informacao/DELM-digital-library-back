package br.senac.backend.model.games;

public class Game {

	private Integer id;
	private String nome;
	private Double preco;
	private String dataLancamento;
	private String desenvolvedor;
	private String descricao;
	private Integer idCategoria;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getDesenvolvedor() {
		return desenvolvedor;
	}

	public void setDesenvolvedor(String desenvolvedor) {
		this.desenvolvedor = desenvolvedor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", nome=" + nome + ", preco=" + preco + ", dataLancamento=" + dataLancamento
				+ ", desenvolvedor=" + desenvolvedor + ", descricao=" + descricao + ", idCategoria=" + idCategoria
				+ "]";
	}

}
