package br.senac.backend.validators;

import java.sql.SQLException;

import javax.ws.rs.core.Response;

import br.senac.backend.db.dao.DaoUser;
import br.senac.backend.db.utils.DateUtils;
import br.senac.backend.db.utils.ResponseUtils;
import br.senac.backend.models.User;

public class UserValidator {

	public static Response validateUser(User usuario) {
		if (validateNickname(usuario.getNickname()) != null)
			return validateNickname(usuario.getNickname());

		if (validateName(usuario.getNome()) != null)
			return validateName(usuario.getNome());

		if (validateEmail(usuario.getEmail()) != null)
			return validateEmail(usuario.getEmail());

		if (validatePassword(usuario.getSenha()) != null)
			return validatePassword(usuario.getSenha());

		if (validateCash(usuario.getSaldo()) != null)
			return validateCash(usuario.getSaldo());

		if (validateDate(usuario.getDataCriacao()) != null)
			return validateDate(usuario.getDataCriacao());

		if (usuario.getNickname().isEmpty() && usuario.getNome().isEmpty() && usuario.getEmail().isEmpty()
				&& usuario.getSenha().isEmpty()) {
			return ResponseUtils.successReturnString(Response.Status.OK, "� necess�rio fornecer um usu�rio");
		}

		return null;
	}

	public static Response validateNickname(String nickname) {
		if (nickname.isEmpty())
			return ResponseUtils.successReturnString(Response.Status.OK, "O campo nickname � obrigat�rio");

		if (!nickname.isEmpty()) {
			if (nickname.length() < 5)
				return ResponseUtils.successReturnString(Response.Status.OK,
						"O campo nickname s� pode ter no m�ximo 50 caracteres");

			if (nickname.length() > 50)
				return ResponseUtils.successReturnString(Response.Status.OK,
						"O campo nickname s� pode ter no m�ximo 50 caracteres");
		}

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

	public static Response validateEmail(String email) {
		if (email.isEmpty())
			return ResponseUtils.successReturnString(Response.Status.OK, "O campo e-mail � obrigat�rio");

		if (!email.isEmpty()) {
			if (!email.contains("@"))
				return ResponseUtils.successReturnString(Response.Status.OK, "O formato e-mail est� incorreto");

			if (email.length() > 150)
				return ResponseUtils.successReturnString(Response.Status.OK,
						"O campo e-mail s� pode ter no m�ximo 150 caracteres");
		}

		return null;
	}

	public static Response validatePassword(String senha) {
		if (senha.isEmpty())
			return ResponseUtils.successReturnString(Response.Status.OK, "O campo senha � obrigat�rio");

		if (!senha.isEmpty()) {
			if (senha.length() < 6)
				return ResponseUtils.successReturnString(Response.Status.OK,
						"O campo senha precisar ter no m�nimo 6 caracteres");

			if (senha.length() > 70)
				return ResponseUtils.successReturnString(Response.Status.OK,
						"O campo senha s� pode ter no m�ximo 70 caracteres");
		}

		return null;
	}

	public static Response validateCash(Double saldo) {
		if (saldo.isNaN())
			return ResponseUtils.successReturnString(Response.Status.OK, "O campo saldo s� aceita n�meros");

		if (saldo < 0)
			return ResponseUtils.successReturnString(Response.Status.OK, "O campo saldo precisa ser positivo");

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

	public static Response userExists(String nickname, String email) {
		try {
			User userByNickname = DaoUser.findByNickname(nickname);
			if (userByNickname != null)
				return ResponseUtils.successReturnString(Response.Status.OK,
						"J� existe um usu�rio com esse nickname");

			User userByEmail = DaoUser.findByEmail(email);
			if (userByEmail != null)
				return ResponseUtils.successReturnString(Response.Status.OK,
						"J� existe um usu�rio com esse e-mail");
		} catch (SQLException e) {
			e.printStackTrace();
			return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST, "Erro: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseUtils.successReturnString(Response.Status.BAD_REQUEST, "Erro: " + e.getMessage());
		}

		return null;
	}

	public static Response userExistsToUpdate(String nickname, String email, Integer id) {
		try {
			User userByNickname = DaoUser.findByNickname(nickname);
			if (userByNickname != null) {
				if (!id.equals(userByNickname.getId()))
				return ResponseUtils.successReturnString(Response.Status.OK,
						"J� existe um usu�rio com esse nickname");
			}

			User userByEmail = DaoUser.findByEmail(email);
			if (userByEmail != null) {
				if (!id.equals(userByEmail.getId())) 
				return ResponseUtils.successReturnString(Response.Status.OK,
						"J� existe um usu�rio com esse e-mail");
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
