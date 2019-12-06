package br.senac.backend.dto;

public class HistoricForm {

	Integer codigoCompra;
	String jogoNome;
	Double precoJogo;
	String dataCompra;
	Double totalCompra;

	public Integer getCodigoCompra() {
		return codigoCompra;
	}

	public void setCodigoCompra(Integer codigoCompra) {
		this.codigoCompra = codigoCompra;
	}

	public String getJogoNome() {
		return jogoNome;
	}

	public void setJogoNome(String jogoNome) {
		this.jogoNome = jogoNome;
	}

	public Double getPrecoJogo() {
		return precoJogo;
	}

	public void setPrecoJogo(Double precoJogo) {
		this.precoJogo = precoJogo;
	}

	public String getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Double getTotalCompra() {
		return totalCompra;
	}

	public void setTotalCompra(Double totalCompra) {
		this.totalCompra = totalCompra;
	}

}
