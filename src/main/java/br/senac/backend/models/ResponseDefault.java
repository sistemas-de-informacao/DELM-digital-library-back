package br.senac.backend.models;

import javax.ws.rs.core.Response.Status;

public class ResponseDefault {

	Integer code;
	String mensagem;
	Object body;

	public ResponseDefault(Status code, String mensagem, Object body) {
		this.code = code.getStatusCode();
		this.mensagem = mensagem;
		this.body = body;
	}

	public ResponseDefault(Status code, String mensagem) {
		this.code = code.getStatusCode();
		this.mensagem = mensagem;
	}

	public Integer getCode() {
		return code;
	}

	public String getMensagem() {
		return mensagem;
	}

	public Object getBody() {
		return body;
	}

}
