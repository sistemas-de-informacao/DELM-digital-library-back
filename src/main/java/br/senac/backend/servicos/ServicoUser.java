package br.senac.backend.servicos;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.senac.backend.db.dao.DaoUser;
import br.senac.backend.model.users.User;


@Path("/user")
public class ServicoUser {

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void inserirUser(User user) {
		try {
			DaoUser.inserir(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> listarUsers() {
		try {
			return DaoUser.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizarUser(User user) {
		try {
			DaoUser.atualizar(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void removerGame(@PathParam("id") Integer id) {
		try {
			DaoUser.excluir(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
