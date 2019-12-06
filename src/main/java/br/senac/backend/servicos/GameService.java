package br.senac.backend.servicos;

import java.sql.SQLException;
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
import br.senac.backend.db.dao.DaoLibrary;
import br.senac.backend.db.utils.ResponseUtils;
import br.senac.backend.models.Game;
import br.senac.backend.validators.GameValidator;

@Path("/game")
public class GameService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addGame(Game game) {
		try {
			if (GameValidator.validateGame(game) != null)
				return GameValidator.validateGame(game);

			if (GameValidator.gameExists(game.getNome()) != null)
				return GameValidator.gameExists(game.getNome());

			Integer id = DaoGame.insert(game);
			game.setId(id);
			return ResponseUtils.successReturnBody(Response.Status.OK, "Jogo cadastrado com sucesso", game);
		} catch (Exception e) {
			return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST,
					"Erro ao cadastrar jogo: " + e.getMessage());
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "/delm/{user}")
	public List<Game> listGames(@PathParam(value = "user") Integer id) {
		try {
			System.out.println(id);
			if (id == -1) {
				return DaoGame.list();
			} else if (id >= 0 ) {
				return DaoLibrary.findAllByUsuario(id);
			} else if (id == -2) {
				return DaoGame.listAll();
			}
		} catch (Exception e) {
			System.out.println("Erro identificado em 'listGame': " + e.getMessage());
		}
		return null;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateGame(Game game) {
		try {
			if (GameValidator.validateGame(game) != null)
				return GameValidator.validateGame(game);

			if (GameValidator.gameExistsToUpdate(game.getNome(), game.getId()) != null)
				return GameValidator.gameExistsToUpdate(game.getNome(), game.getId());

			DaoGame.update(game);
			return ResponseUtils.successReturnBody(Response.Status.OK, "Jogo atualizado com sucesso", game);
		} catch (Exception e) {
			return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST,
					"Erro ao atualizar jogo: " + e.getMessage());
		}
	}

	@PUT
	@Path(value = "/image")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateGameByName(Game game) {
		try {
			System.out.println(game.getFullPath());
			DaoGame.updateByName(game);
			return ResponseUtils.successReturnBody(Response.Status.OK, "Jogo atualizado com sucesso", game);
		} catch (Exception e) {
			return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST,
					"Erro ao atualizar jogo: " + e.getMessage());
		}
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response removeGame(@PathParam("id") Integer id) {
		try {
			DaoGame.delete(id);
			return Response.status(Response.Status.OK).entity("Jogo excluído com sucesso.").build();
		} catch (Exception e) {
			return Response.status(Response.Status.OK).entity("Não foi possível excluir jogo: " + e.getMessage())
					.build();
		}
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "{id}")
	public Game findById(@PathParam("id") Integer id) {
		try {
			if (DaoGame.findById(id) == null) {
				return null;
			} else {
				return DaoGame.findById(id);
			}
		} catch (Exception e) {
			Response.status(Response.Status.OK).entity("Não foi possível encontrar jogo: " + e.getMessage()).build();
		}

		return null;
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "pesquisar/{nome}")
	public Response getAllByNome(@PathParam("nome") String nome) {
		try {
			if (DaoGame.findAllByName(nome) != null)
				return ResponseUtils.successReturnBody(Response.Status.OK, "Jogos encontrados com sucesso",
						DaoGame.findAllByName(nome));
		} catch (Exception e) {
			return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST,
					"Erro ao pesquisar jogo: " + e.getMessage());
		}

		return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST, "Jogo não encontrado");
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "has-jogo/{game}/{usuario}")
	public boolean hasJogo(@PathParam("game") Integer game, @PathParam("usuario") Integer usuario) {
		try {
			return DaoLibrary.findByUsuarioAndJogo(game, usuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

}
