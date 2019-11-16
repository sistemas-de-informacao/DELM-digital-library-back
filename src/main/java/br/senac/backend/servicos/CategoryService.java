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
import br.senac.backend.db.utils.ResponseUtils;
import br.senac.backend.models.Category;
import br.senac.backend.validators.CategoryValidator;

@Path("/category")
public class CategoryService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(Category category) {
		try {
			if (CategoryValidator.validateGame(category) != null)
				return CategoryValidator.validateGame(category);

			if (CategoryValidator.categoryExists(category.getNome()) != null)
				return CategoryValidator.categoryExists(category.getNome());

			DaoCategory.insert(category);
			return ResponseUtils.successReturnBody(Response.Status.CREATED, "Categoria criada com sucesso", category);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST,
					"Erro ao cadastrar categoria: " + e.getMessage());
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> list() {
		try {
			if (!DaoCategory.list().isEmpty()) {
				return DaoCategory.list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Category category) {
		try {
			if (CategoryValidator.validateGame(category) != null)
				return CategoryValidator.validateGame(category);

			if (CategoryValidator.categoryToUpdateExists(category.getNome(), category.getId()) != null)
				return CategoryValidator.categoryToUpdateExists(category.getNome(), category.getId());
			
			DaoCategory.update(category);
			return ResponseUtils.successReturnBody(Response.Status.CREATED, "Categoria atualizada com sucesso",
					category);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST,
					"Erro ao cadastrar categoria: " + e.getMessage());
		}
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response delete(@PathParam("id") Integer id) {
		try {
			DaoCategory.delete(id);
			return Response.status(Response.Status.OK).entity("Categoria deletada com sucesso.").build();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.status(Response.Status.BAD_REQUEST).entity("Não foi possãvel excluir categoria selecionada")
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
					"Não foi possãvel executar 'findById' em Category. \n" + "Erro identificado: " + e.getMessage());
		}

		return null;
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path(value = "pesquisar/{nome}")
	public Response getAllByNome(@PathParam("nome") String nome) {
		try {
			if (DaoCategory.findAllByName(nome) != null)
				return ResponseUtils.successReturnBody(Response.Status.OK, "Categorias encontradas com sucesso", DaoCategory.findAllByName(nome));
		} catch (Exception e) {
			return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST,
					"Erro ao pesquisar categoria: " + e.getMessage());
		}

		return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST,
				"Categoria não encontrada" );
	}

}
