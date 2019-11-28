package br.senac.backend.models;

public class Historic {

	private Long id;
	private int codCompra;
	private String dataCompra;
	private Long idJogo;
	private Long idUsuario;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getCodCompra() {
		return codCompra;
	}
	public void setCodCompra(int codCompra) {
		this.codCompra = codCompra;
	}
	public String getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}
	public Long getIdJogo() {
		return idJogo;
	}
	public void setIdJogo(Long idJogo) {
		this.idJogo = idJogo;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
	
	
	
	
}
