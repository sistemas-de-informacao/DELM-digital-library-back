package br.senac.backend.models;

public enum Permissoes {

	ADMINISTRADOR, NORMAL;

	public static Permissoes getPermissao(Integer ordinal) {
		return ordinal == ADMINISTRADOR.ordinal() ? ADMINISTRADOR : NORMAL;
	}
	
}
