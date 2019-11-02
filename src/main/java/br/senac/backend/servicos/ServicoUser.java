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
import javax.ws.rs.core.Response;

import br.senac.backend.db.dao.DaoGame;
import br.senac.backend.db.dao.DaoUser;
import br.senac.backend.dto.loginDTO;
import br.senac.backend.model.games.Game;
import br.senac.backend.model.users.User;

@Path("/user")
public class ServicoUser {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(User user) {
		try {
			DaoUser.inserir(user);
			return Response.status(Response.Status.OK)
					.entity("Usuário cadastrado com sucesso. \n" + "Usuário cadastrado: " + user.getNickname()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.OK)
					.entity("Erro ao cadastrar usuário." + "Erro identificado em 'addUser': " + e.getMessage()).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> listUsers() {
		try {
			return DaoUser.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(User user) {
		try {
			DaoUser.atualizar(user);
			return Response.status(Response.Status.OK)
					.entity("Informações de: " + user.getNome() + " foram atualizadas com sucesso.").build();
		} catch (Exception e) {
			return Response.status(Response.Status.OK)
					.entity("Erro ao atualizar usuário. \n" + "Erro identificado em 'updateUser': " + e.getMessage())
					.build();
		}
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response removeUser(@PathParam("id") Integer id) {
		try {
			DaoUser.excluir(id);
			return Response.status(Response.Status.OK).entity("Usuário deletado com sucesso.").build();

		} catch (Exception e) {
			return Response.status(Response.Status.OK)
					.entity("Erro ao deletar usuário. \n" + "Erro identificado em 'removeUser': " + e.getMessage())
					.build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "/login")
	public User loginAccount(loginDTO login) {
		try {
			User usuarioLogin = DaoUser.listarByNick(login.getUser());
			if (login.getUser() == usuarioLogin.getNickname()) {
				if (login.getSenha() == usuarioLogin.getSenha()) {
					return usuarioLogin;
				} else {
					System.out.println("Senha está incorreta. \n" + "Verifique os dados inseridos.");
				}
			} else {
				System.out.println("Usuario não existe em nossa base de dados \n" + "Verifique os dados inseridos.");
			}
		} catch (Exception e) {
			System.err.println("Erro ao efetuar login \n" + "Erro identificado: " + e.getMessage());
		}
		return null;
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "{id}")
	public User findById(@PathParam("id") Integer id) {
		try {
			if (DaoUser.findById(id) == null) {
				System.out.println("Não foi encontrado nenhum usuário com esse id.");
				return null;
			} else {
				System.out.println("Usuário encontrado: " + DaoUser.findById(id).getNome());
				return DaoUser.findById(id);
			}
		} catch (Exception e) {
			System.out.println(
					"Não foi possível executar 'findById' em User. \n" + "Erro identificado: " + e.getMessage());
		}
		return null;

	}

}
