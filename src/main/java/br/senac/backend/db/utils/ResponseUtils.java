package br.senac.backend.db.utils;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.senac.backend.models.ResponseDefault;

public class ResponseUtils {

	public static Response successReturnString(Status code, String mensagem) {
		ResponseDefault res = new ResponseDefault(code, mensagem);
		return Response.status(Response.Status.OK).entity(res).build();
	}

	public static Response successReturnBody(Status code, String mensagem, Object body) {
		ResponseDefault res = new ResponseDefault(code, mensagem, body);
		return Response.status(Response.Status.OK).entity(res).build();
	}

}
