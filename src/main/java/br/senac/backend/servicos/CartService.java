package br.senac.backend.servicos;

import java.util.List;
import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import br.senac.backend.models.Cart;

@Path("/buy")
public class CartService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void inserirCarrinho(Cart cart) {
	
		Random grn = new Random();
		int codCompra = grn.nextInt(100000) + 1;
		
		
		
	}

}
