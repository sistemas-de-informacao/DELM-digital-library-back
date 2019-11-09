package br.senac.backend.validators;

import java.sql.SQLException;

import javax.ws.rs.core.Response;

import br.senac.backend.db.dao.DaoCategory;
import br.senac.backend.db.dao.DaoGame;
import br.senac.backend.db.utils.DateUtils;
import br.senac.backend.db.utils.ResponseUtils;
import br.senac.backend.models.Game;

public class GameValidator {

	public static Response validateGame(Game game) {
		if (validateName(game.getNome()) != null)
			return validateName(game.getNome());

		if (validatePreco(game.getPreco()) != null)
			return validatePreco(game.getPreco());

		if (validateDate(game.getDataLancamento()) != null)
			return validateDate(game.getDataLancamento());

		if (validateDev(game.getDesenvolvedor()) != null)
			return validateDev(game.getDesenvolvedor());

		if (validateDescription(game.getDescricao()) != null)
			return validateDescription(game.getDescricao());

		if (validateCategory(game.getIdCategoria()) != null)
			return validateCategory(game.getIdCategoria());

		return null;
	}

	public static Response validateName(String nome) {
		if (nome.isEmpty())
			return ResponseUtils.successReturnString(Response.Status.OK, "O campo nome � obrigat�rio");

		if (!nome.isEmpty()) {
			if (nome.length() > 120)
				return ResponseUtils.successReturnString(Response.Status.OK,
						"O campo nome s� pode ter no m�ximo 120 caracteres");
		}

		return null;
	}

	public static Response validatePreco(Double preco) {
		if (preco.isNaN())
			return ResponseUtils.successReturnString(Response.Status.OK, "O campo pre�o precisa ser um n�mero");

		if (preco < 0)
			return ResponseUtils.successReturnString(Response.Status.OK, "O pre�o preciso ser positivo");

		return null;
	}

	public static Response validateDate(String data) {
		if (data.isEmpty() || data == null)
			return ResponseUtils.successReturnString(Response.Status.OK, "O campo data � obrigat�rio");

		if (DateUtils.isValidDate(data) == false)
			return ResponseUtils.successReturnString(Response.Status.OK,
					"O formato data est� incorreto - formato: dd/MM/yyyy");

		return null;
	}

	public static Response validateDev(String desenvolvedor) {
		if (desenvolvedor.isEmpty() || desenvolvedor == null)
			return ResponseUtils.successReturnString(Response.Status.OK, "O campo desenvolvedor � obrigat�rio");

		if (desenvolvedor.length() > 45)
			return ResponseUtils.successReturnString(Response.Status.OK,
					"O campo desenvolvedor s� pode ter no m�ximo 45 caracteres");

		return null;
	}

	public static Response validateDescription(String description) {
		if (description.isEmpty() || description == null)
			return ResponseUtils.successReturnString(Response.Status.OK, "O campo descri��o � obrigat�rio");

		if (description.length() > 500)
			return ResponseUtils.successReturnString(Response.Status.OK,
					"O campo descri��o s� pode ter no m�ximo 500 caracteres");

		return null;
	}

	public static Response validateCategory(Integer categoria) {
		if (categoria == null)
			return ResponseUtils.successReturnString(Response.Status.OK, "O campo categoria � obrigat�rio");

		try {
			if (DaoCategory.findById(categoria) == null)
				return ResponseUtils.successReturnString(Response.Status.OK, "A categoria fornecida n�o existe");

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST, "Erro: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST, "Erro: " + e.getMessage());
		}
	}

	public static Response gameExists(String nome) {
		try {
			Game gameByName = DaoGame.findByName(nome);
			if (gameByName != null)
				return ResponseUtils.successReturnString(Response.Status.OK,
						"J� existe um jogo com esse nome");
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST, "Erro: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST, "Erro: " + e.getMessage());
		}

		return null;
	}

	public static Response gameExistsToUpdate(String nome, Integer id) {
		try {
			Game gameByName = DaoGame.findByName(nome);
			if (gameByName != null) {
				if (!id.equals(gameByName.getId()))
					return ResponseUtils.successReturnString(Response.Status.OK,
							"J� existe um jogo com esse nome");
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
