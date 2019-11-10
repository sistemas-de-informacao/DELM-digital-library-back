package br.senac.backend.validators;

import java.sql.SQLException;

import javax.ws.rs.core.Response;

import br.senac.backend.db.dao.DaoCategory;
import br.senac.backend.db.utils.ResponseUtils;
import br.senac.backend.models.Category;

public class CategoryValidator {

	public static Response validateGame(Category category) {
		if (validateName(category.getNome()) != null)
			return validateName(category.getNome());

		return null;
	}

	public static Response validateName(String nome) {
		if (nome.isEmpty())
			return ResponseUtils.successReturnString(Response.Status.OK, "O campo nome é obrigatório");

		if (!nome.isEmpty()) {
			if (nome.length() > 120)
				return ResponseUtils.successReturnString(Response.Status.OK,
						"O campo nome só pode ter no máximo 120 caracteres");
		}

		return null;
	}

	public static Response categoryExists(String nome) {
		try {
			Category categoria = DaoCategory.findByName(nome);
			if (categoria != null)
				return ResponseUtils.successReturnString(Response.Status.OK, "Já existe uma categoria com esse nome");
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST, "Erro: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST, "Erro: " + e.getMessage());
		}

		return null;
	}

	public static Response categoryToUpdateExists(String nome, Integer id) {
		try {
			Category categoria = DaoCategory.findByName(nome);
			if (categoria != null) {
				if (!categoria.getId().equals(id))
					return ResponseUtils.successReturnString(Response.Status.OK,
							"Já existe uma categoria com esse nome");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST, "Erro: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST, "Erro: " + e.getMessage());
		}

		return null;
	}

}
