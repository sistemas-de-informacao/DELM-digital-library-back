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

import br.senac.backend.db.dao.DaoGame;
import br.senac.backend.model.games.Game;

@Path("/games")
public class ServicoGame {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void inserirGames(Game game) {
		try {
			DaoGame.inserir(game);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Game> listarGames() {
		try {
			return DaoGame.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizarGame(Game game) {
		try {
			DaoGame.atualizar(game);
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
