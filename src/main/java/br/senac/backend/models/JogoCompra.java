package br.senac.backend.models;

public class JogoCompra {

	Integer idJogo;
	Integer idCompra;
	Double PrecoJogo;

	public Integer getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(Integer idJogo) {
		this.idJogo = idJogo;
	}

	public Integer getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Integer idCompra) {
		this.idCompra = idCompra;
	}

	public Double getPrecoJogo() {
		return PrecoJogo;
	}

	public void setPrecoJogo(Double precoJogo) {
		PrecoJogo = precoJogo;
	}

}
