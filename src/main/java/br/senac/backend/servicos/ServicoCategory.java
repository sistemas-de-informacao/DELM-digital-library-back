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
import br.senac.backend.db.dao.DaoUser;
import br.senac.backend.model.category.Category;
import br.senac.backend.model.users.User;

@Path("/categoria")
public class ServicoCategory {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inserirCategoria(Category category) {
		try {
			if (DaoCategory.listarByNome(category.getNome_categoria()).get(0).equals(null)) {
//				DaoUser.inserir(user);
				return Response.status(Response.Status.OK).entity("Usu�rio cadastrado com sucesso.").build();
			}else {
				return Response.status(Response.Status.BAD_REQUEST).entity("Usu�rio j� existe").build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.status(Response.Status.BAD_REQUEST).entity("Os dados fornecidos est�o incorretos.").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> listarUsers() {
		try {
			if(!DaoUser.listar().isEmpty()) {
				return DaoUser.listar();
			}else {
				System.err.println("");
			}
			} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	@PUT
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response atualizarUser(User user) {
//		try {
////			if(DaoUser.listarByEmail(user.getEmail()).get(0).equals(null) &&
////					DaoUser.listarByNick(user.getNickname()).get(0).equals(null)) {
//				DaoUser.atualizar(user);
////				return Response.status(Response.Status.OK).entity("Informa��es atualizadas com sucesso.").build();
////					
////			}else {
////				return Response.status(Response.Status.BAD_REQUEST).entity("Informa��es j� existentes na base de dados, favor revise-as").build();
//				
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

//	@DELETE
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Path("/{id}")
//	public Response removerUser(@PathParam("id") Integer id) {
//		try {
//			if(!DaoUser.findById(id).isEmpty()) {
//				DaoUser.excluir(id);
//				return Response.status(Response.Status.OK).entity("Usu�rio deletado com sucesso.").build();
//				
//			}else {
//				return Response.status(Response.Status.BAD_REQUEST).entity("N�o foi possivel deletar \n Usu�rio n�o existe.")
//						.build();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return Response.status(Response.Status.BAD_REQUEST).entity("N�o foi poss�vel excluir usu�rio selecionado")
//				.build();
//	}

	
}
