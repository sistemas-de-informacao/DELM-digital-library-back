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
	public Response inserirGames(Game game) {
		try {
			if (DaoGame.listarGamesByName(game.getNome()).get(0).getNome().equals(null)
					&& DaoGame.listarGamesByDesc(game.getDescricao()).get(0).getDescricao().equals(null)) {
				DaoGame.inserir(game);
				return Response.status(Response.Status.OK).entity("Jogo cadastrado com sucesso.").build();
			} else {
				return Response.status(Response.Status.BAD_REQUEST).entity("Jogo j� cadastrado.")
						.build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Game> listarGames() {
		try {
			return DaoGame.listarGames();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizarGame(Game game) {
		try {
			DaoGame.update(game);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public void removerGame(@PathParam("id") Integer id) {
		try {
			DaoGame.excluir(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
