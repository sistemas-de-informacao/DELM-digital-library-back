package validators;

import javax.ws.rs.core.Response;

import br.senac.backend.model.users.User;

public class UserValidator {

	public static Response validateUser(User usuario) {
		if (usuario.getNickname().equals(null) && usuario.getNome().equals(null) && usuario.getEmail().equals(null)
				&& usuario.getSenha().equals(null)) {
			return Response.status(Response.Status.OK).entity("É necessário fornecer um usuário").build();
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
			return Response.status(Response.Status.OK).entity("O campo nickname é obrigatório").build();

		if (nickname.length() < 5)
			return Response.status(Response.Status.OK).entity("O campo nickname precisar ter no mínimo 5 caracteres")
					.build();

		if (nickname.length() > 50)
			return Response.status(Response.Status.OK).entity("O campo nickname só pode ter no máximo 50 caracteres")
					.build();

		return null;
	}

	public static Response validateName(String nome) {
		if (nome.equals(null) || nome.isEmpty() || nome == null || nome.equals(""))
			return Response.status(Response.Status.OK).entity("O campo nome é obrigatório").build();

		if (nome.length() > 120)
			return Response.status(Response.Status.OK).entity("O campo nome só pode ter no máximo 120 caracteres")
					.build();

		return null;
	}

	public static Response validateEmail(String email) {
		if (email.equals(null) || email.isEmpty() || email == null || email.equals(""))
			return Response.status(Response.Status.OK).entity("O campo e-mail é obrigatório").build();

		if (!email.contains("@"))
			return Response.status(Response.Status.OK).entity("O formato e-mail está incorreto").build();

		if (email.length() > 150)
			return Response.status(Response.Status.OK).entity("O campo e-mail só pode ter no máximo 150 caracteres")
					.build();

		return null;
	}

	public static Response validatePassword(String senha) {
		if (senha.equals(null) || senha.isEmpty() || senha == null)
			return Response.status(Response.Status.OK).entity("O campo senha é obrigatório").build();

		if (senha.length() < 6)
			return Response.status(Response.Status.OK).entity("O campo senha precisar ter no mínimo 6 caracteres")
					.build();

		if (senha.length() > 70)
			return Response.status(Response.Status.OK).entity("O campo senha só pode ter no máximo 70 caracteres")
					.build();

		return null;
	}

}
