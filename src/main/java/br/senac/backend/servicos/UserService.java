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

import br.senac.backend.db.dao.DaoUser;
import br.senac.backend.db.utils.ResponseUtils;
import br.senac.backend.dto.LoginDTO;
import br.senac.backend.dto.UpdatePasswordDTO;
import br.senac.backend.models.User;
import br.senac.backend.validators.UserValidator;

@Path("/user")
public class UserService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(User user) {
		try {
			if (UserValidator.validateUser(user) != null)
				return UserValidator.validateUser(user);

			if (UserValidator.validateUser(user) != null)
				return UserValidator.validateUser(user);

			if (UserValidator.userExists(user.getNickname(), user.getEmail()) != null)
				return UserValidator.userExists(user.getNickname(), user.getEmail());

			DaoUser.inserir(user);
			return ResponseUtils.successReturnBody(Response.Status.CREATED, "Usuário criado com sucesso", user);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST,
					"Erro ao cadastrar usuário: " + e.getMessage());
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
			if (UserValidator.validateUser(user) != null)
				return UserValidator.validateUser(user);

			if (UserValidator.userExistsToUpdate(user.getNickname(), user.getEmail(), user.getId()) != null)
				return UserValidator.userExistsToUpdate(user.getNickname(), user.getEmail(), user.getId());

			DaoUser.atualizar(user);
			return ResponseUtils.successReturnBody(Response.Status.OK, "Usuário atualizado com sucesso", user);
		} catch (Exception e) {
			return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST,
					"Erro ao atualizar usuário: " + e.getMessage());
		}
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response removeUser(@PathParam("id") Integer id) {
		try {
			DaoUser.excluir(id);
			return ResponseUtils.successReturnString(Response.Status.OK, "Usuário deletado com sucesso");
		} catch (Exception e) {
			return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST,
					"Erro ao deletar usuário: " + e.getMessage());
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "/login")
	public Response loginAccount(LoginDTO login) {
		try {
			User usuario = new User();
	
			if (login.getUser().contains("@")) {
				usuario = DaoUser.findByEmail(login.getUser());
				if (usuario == null)
					return ResponseUtils.successReturnString(Response.Status.OK,
							"Usuario não existe em nossa base de dados. Verifique os dados inseridos.");
			} else {
				usuario = DaoUser.findByNickname(login.getUser());
			}

			if (login.getUser().trim().equalsIgnoreCase(usuario.getNickname()) 
					|| login.getUser().trim().equalsIgnoreCase(usuario.getEmail())) {
				if (login.getSenha().trim().equalsIgnoreCase(usuario.getSenha())) {
					if (usuario.getEnable().equals(false))
						return ResponseUtils.successReturnString(Response.Status.OK, "Conta desativada");

					return ResponseUtils.successReturnBody(Response.Status.OK, "Usuário logado com sucesso.", usuario);
				} else {
					return ResponseUtils.successReturnString(Response.Status.OK,
							"Senha está incorreta. Verifique os dados inseridos.");
				}
			} else {
				return ResponseUtils.successReturnString(Response.Status.OK,
						"Usuario não existe em nossa base de dados. Verifique os dados inseridos.");
			}
		} catch (Exception e) {
			return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST,
					"Erro ao efetuar login: " + e.getMessage());
		}
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

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "pesquisar/{nickname}")
	public Response getAllByNome(@PathParam("nickname") String nickname) {
		try {
			if (DaoUser.findAllByNickname(nickname) != null)
				return ResponseUtils.successReturnBody(Response.Status.OK, "Usuários encontrados com sucesso",
						DaoUser.findAllByNickname(nickname));
		} catch (Exception e) {
			return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST,
					"Erro ao pesquisar usuário: " + e.getMessage());
		}

		return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST, "Usuário não encontrado");
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "ativar/{nickname}")
	public Response ativar(@PathParam("nickname") String nickname) {
		try {
			if (DaoUser.ativar(nickname) != null)
				return ResponseUtils.successReturnBody(Response.Status.OK, "Usuário ativado com sucesso",
						DaoUser.ativar(nickname));
		} catch (Exception e) {
			return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST,
					"Erro ao ativar usuário: " + e.getMessage());
		}

		return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST, "Não foi possível ativar usuário");
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "trocar-senha/{id}")
	public Response updatePassword(@PathParam("id") Integer id, UpdatePasswordDTO senhas) {
		try {
			if (UserValidator.passwordsIsEquals(senhas) != null)
				return UserValidator.passwordsIsEquals(senhas);

			if (DaoUser.updatePassword(senhas, id) != null)
				return DaoUser.updatePassword(senhas, id);

		} catch (Exception e) {
			return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST,
					"Erro ao alterar senha do usuário: " + e.getMessage());
		}

		return ResponseUtils.successReturnString(Response.Status.OK, "Senha alterada com sucesso");
	}

}
