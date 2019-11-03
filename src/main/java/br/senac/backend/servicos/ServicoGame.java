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
import br.senac.backend.model.games.Game;

@Path("/games")
public class ServicoGame {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addGames(Game game) {
		try {
			DaoGame.inserir(game);
			return Response.status(Response.Status.OK)
					.entity("Jogo cadastrado com sucesso. \n" + "Jogo cadastrado: " + game.getNome()).build();
		} catch (Exception e) {
			return Response.status(Response.Status.OK)
					.entity("Erro ao cadastrar jogo." + "Erro identificado em 'addGame': " + e.getMessage()).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Game> listGames() {
		try {
			return DaoGame.listarGames();
		} catch (Exception e) {
			System.out.println("Erro identificado em 'listGame': " + e.getMessage());
		}
		return null;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateGame(Game game) {
		try {
			DaoGame.update(game);
			return Response.status(Response.Status.OK).entity("Informações atualizadas com sucesso.").build();
		} catch (Exception e) {
			return Response.status(Response.Status.OK).entity("Não foi possível atualizar as informações \n"
					+ "Erro identificado em 'updateGame': " + e.getMessage()).build();
		}
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response removeGame(@PathParam("id") Integer id) {
		try {
			DaoGame.excluir(id);
			return Response.status(Response.Status.OK).entity("Jogo excluído com sucesso.").build();
		} catch (Exception e) {
			return Response.status(Response.Status.OK).entity("Não foi possível excluir jogo. \n"
			+ "Erro identificado em 'removeGame': " + e.getMessage()).build();

		}
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "{id}")
	public Game findById(@PathParam("id") Integer id) {
		try {
			if (DaoGame.findById(id) == null) {
				System.out.println("Não foi encontrado nenhum jogo com esse id.");
				return null;
			} else {
				System.out.println("Jogo encontrado: " + DaoGame.findById(id).getNome());
				return DaoGame.findById(id);
			}
		} catch (Exception e) {
			System.out.println("Não foi possível executar 'findById' em Game. \n"
					+ "Erro identificado: " + e.getMessage());
		}
		return null;

	}
}
