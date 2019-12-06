package br.senac.backend.servicos;

import java.sql.SQLException;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.senac.backend.db.dao.DaoBuy;
import br.senac.backend.db.dao.DaoLibrary;
import br.senac.backend.db.utils.ResponseUtils;
import br.senac.backend.models.Cart;

@Path("/buy")
public class BuyService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inserirCarrinho(Cart cart) {
		try {
			if (UserService.isDinheiroSuficiente(cart.getComprador().getId(), cart.getTotalCompra()) == false)
				return ResponseUtils.successReturnString(Response.Status.OK,
						"Compra falhou, usuário não possui saldo suficiente");

			Integer id = DaoBuy.insert(cart);
			criarTabelaCompraJogo(id, cart);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseUtils.successReturnString(Response.Status.OK, "Compra realizada com sucesso");
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Path(value = "{id}")
	public Response getHistoricoByUser(@PathParam(value = "id") Integer id) {
		try {
			System.out.println("entrei");
			return ResponseUtils.successReturnBody(Response.Status.OK, "Histórico de vendas gerado com sucesso", DaoBuy.getAllCodigosByUser(id));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseUtils.successReturnString(Response.Status.OK, "Compra realizada com sucesso");
	}

	public static int gerarCodidoCompra() {
		Random grn = new Random();
		return grn.nextInt(100000) + 1;
	}

	public boolean criarTabelaCompraJogo(Integer id, Cart cart) {
		if (id != null) {
			cart.getJogos().stream().forEach((jogo) -> {
				try {
					DaoBuy.insertJogoComprado(id, jogo);
					DaoLibrary.insert(jogo.getId(), cart.getComprador().getId());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

			return true;
		}

		return false;
	}

}
