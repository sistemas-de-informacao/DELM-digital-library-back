package br.senac.backend.servicos;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import br.senac.backend.db.dao.DaoHistoric;
import br.senac.backend.models.Historic;

@Path("/historico")
public class HistoricService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void insertHistoric(Historic historic) throws SQLException, Exception {
	
			DaoHistoric.insert(historic);
		
	}
	
}
