package br.senac.backend.dto;

public class LoginDTO {

	String user;
	String senha;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "LoginDTO [user=" + user + ", senha=" + senha + ", getUser()=" + getUser() + ", getSenha()=" + getSenha()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
