package br.senac.backend.models;

import java.util.List;

public class Cart {

	private List<Game> jogos;
	private String dataCompra;
	private Double totalCompra;
	private User comprador;

	@Override
	public String toString() {
		return "Cart [jogos=" + jogos + ", dataCompra=" + dataCompra + ", totalCompra=" + totalCompra + ", comprador="
				+ comprador + "]";
	}

	public List<Game> getJogos() {
		return jogos;
	}

	public void setJogos(List<Game> jogos) {
		this.jogos = jogos;
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

	public User getComprador() {
		return comprador;
	}

	public void setComprador(User comprador) {
		this.comprador = comprador;
	}

}
