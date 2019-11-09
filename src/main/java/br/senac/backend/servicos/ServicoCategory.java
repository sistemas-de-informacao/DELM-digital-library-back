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

import br.senac.backend.db.dao.DaoCategory;
import br.senac.backend.db.dao.DaoGame;
import br.senac.backend.db.dao.DaoUser;
import br.senac.backend.models.Category;
import br.senac.backend.models.User;

@Path("/categoria")
public class ServicoCategory {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertCategory(Category category) {
		try {
			DaoCategory.insertCategory(category);
			return Response.status(Response.Status.OK).entity("Categoria cadastrada com sucesso.").build();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.status(Response.Status.OK).entity("Os dados fornecidos estão incorretos.").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> listCategory() {
		try {
			if (!DaoCategory.listCategory().isEmpty()) {
				return DaoUser.listar();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCategory(Category category) {
		try {

			DaoCategory.updateCategory(category);
			return Response.status(Response.Status.OK).entity("Informações atualizadas com sucesso.").build();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response removeCategory(@PathParam("id") Integer id) {
		try {

			DaoCategory.deleteCategory(id);
			return Response.status(Response.Status.OK).entity("Categoria deletada com sucesso.").build();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.BAD_REQUEST).entity("Não foi possível excluir categoria selecionada")
				.build();
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "{id}")
	public Category findById(@PathParam("id") Integer id) {
		try {
			if (DaoCategory.findById(id) == null) {
				System.out.println("Não foi encontrado nenhuma categoria com esse id.");
				return null;
			} else {
				System.out.println("Jogo encontrado: " + DaoGame.findById(id).getNome());
				return DaoCategory.findById(id);
			}
		} catch (Exception e) {
			System.out.println(
					"Não foi possível executar 'findById' em Category. \n" + "Erro identificado: " + e.getMessage());
		}
		return null;

	}

}
