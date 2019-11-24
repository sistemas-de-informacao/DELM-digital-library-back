package br.senac.backend.dto;

public class UpdatePasswordDTO {

	String senhaAntiga;
	String senhaNova;
	String senhaNovaConfirmar;

	public String getSenhaAntiga() {
		return senhaAntiga;
	}

	public void setSenhaAntiga(String senhaAntiga) {
		this.senhaAntiga = senhaAntiga;
	}

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}

	public String getSenhaNovaConfirmar() {
		return senhaNovaConfirmar;
	}

	public void setSenhaNovaConfirmar(String senhaNovaConfirmar) {
		this.senhaNovaConfirmar = senhaNovaConfirmar;
	}

	@Override
	public String toString() {
		return "UpdatePasswordDTO [senhaAntiga=" + senhaAntiga + ", senhaNova=" + senhaNova + ", senhaNovaConfirmar="
				+ senhaNovaConfirmar + ", getSenhaAntiga()=" + getSenhaAntiga() + ", getSenhaNova()=" + getSenhaNova()
				+ ", getSenhaNovaConfirmar()=" + getSenhaNovaConfirmar() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
