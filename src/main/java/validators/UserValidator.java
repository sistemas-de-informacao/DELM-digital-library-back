package validators;

import javax.ws.rs.core.Response;

import br.senac.backend.model.users.User;

public class UserValidator {

	public static Response validateUser(User usuario) {
		if (usuario.getNickname().equals(null) && usuario.getNome().equals(null) && usuario.getEmail().equals(null)
				&& usuario.getSenha().equals(null)) {
			return Response.status(Response.Status.OK).entity("� necess�rio fornecer um usu�rio").build();
		}

		if (!validateNickname(usuario.getNickname()).equals(null))
			return validateNickname(usuario.getNickname());

		if (!validateName(usuario.getNome()).equals(null))
			return validateName(usuario.getNome());

		if (!validateEmail(usuario.getEmail()).equals(null))
			return validateEmail(usuario.getEmail());

		if (!validatePassword(usuario.getSenha()).equals(null))
			return validatePassword(usuario.getSenha());

		return null;
	}

	public static Response validateNickname(String nickname) {
		System.out.println(nickname.equals(null) || nickname.isEmpty() || nickname == null || nickname.equals(""));
		if (nickname.equals(null) || nickname.isEmpty() || nickname == null || nickname.equals(""))
			return Response.status(Response.Status.OK).entity("O campo nickname � obrigat�rio").build();

		if (nickname.length() < 5)
			return Response.status(Response.Status.OK).entity("O campo nickname precisar ter no m�nimo 5 caracteres")
					.build();

		if (nickname.length() > 50)
			return Response.status(Response.Status.OK).entity("O campo nickname s� pode ter no m�ximo 50 caracteres")
					.build();

		return null;
	}

	public static Response validateName(String nome) {
		if (nome.equals(null) || nome.isEmpty() || nome == null || nome.equals(""))
			return Response.status(Response.Status.OK).entity("O campo nome � obrigat�rio").build();

		if (nome.length() > 120)
			return Response.status(Response.Status.OK).entity("O campo nome s� pode ter no m�ximo 120 caracteres")
					.build();

		return null;
	}

	public static Response validateEmail(String email) {
		if (email.equals(null) || email.isEmpty() || email == null || email.equals(""))
			return Response.status(Response.Status.OK).entity("O campo e-mail � obrigat�rio").build();

		if (!email.contains("@"))
			return Response.status(Response.Status.OK).entity("O formato e-mail est� incorreto").build();

		if (email.length() > 150)
			return Response.status(Response.Status.OK).entity("O campo e-mail s� pode ter no m�ximo 150 caracteres")
					.build();

		return null;
	}

	public static Response validatePassword(String senha) {
		if (senha.equals(null) || senha.isEmpty() || senha == null)
			return Response.status(Response.Status.OK).entity("O campo senha � obrigat�rio").build();

		if (senha.length() < 6)
			return Response.status(Response.Status.OK).entity("O campo senha precisar ter no m�nimo 6 caracteres")
					.build();

		if (senha.length() > 70)
			return Response.status(Response.Status.OK).entity("O campo senha s� pode ter no m�ximo 70 caracteres")
					.build();

		return null;
	}

}
