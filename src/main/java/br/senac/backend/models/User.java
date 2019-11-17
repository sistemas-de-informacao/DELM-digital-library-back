package br.senac.backend.models;

public class User {

	private Integer id;
	private String nickname;
	private String nome;
	private String email;
	private String senha;
	private Double saldo;
	private String dataCriacao;
	private Boolean enable;
	private Permissoes tipo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Permissoes getTipo() {
		return tipo;
	}

	public void setTipo(Permissoes tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nickname=" + nickname + ", nome=" + nome + ", email=" + email + ", senha=" + senha
				+ ", saldo=" + saldo + ", dataCriacao=" + dataCriacao + "]";
	}

}
