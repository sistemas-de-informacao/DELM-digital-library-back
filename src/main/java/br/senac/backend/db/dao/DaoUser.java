package br.senac.backend.db.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import br.senac.backend.db.utils.ConnectionUtils;
import br.senac.backend.model.games.Game;
import br.senac.backend.model.users.User;

public class DaoUser {

	public static void inserir(User user) throws SQLException, Exception {

		String sql = "INSERT INTO tb_usuario (NICK_USUARIO, NOME_USUARIO, EMAIL_USUARIO, SENHA_USUARIO, SALDO_USUARIO, DATA_CRIACAO_USUARIO)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, user.getNickname());
			preparedStatement.setString(2, user.getNome());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getSenha());
			preparedStatement.setDouble(5, user.getSaldo());
			preparedStatement.setString(6, user.getDataCriacao());
			preparedStatement.execute();
		} finally {

			if (preparedStatement != null && !preparedStatement.isClosed()) {
				preparedStatement.close();
			}

			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

	}

	public static void atualizar(User user) throws SQLException, Exception {

		String sql = "UPDATE tb_usuario SET NICK_USUARIO=?, NOME_USUARIO=?,EMAIL_USUARIO=?, SENHA_USUARIO=?, SALDO_USUARIO=?, DATA_CRIACAO_USUARIO=?"
				+ "WHERE (ID_USUARIO=?)";

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, user.getNickname());
			preparedStatement.setString(2, user.getNome());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getSenha());
			preparedStatement.setDouble(5, user.getSaldo());
			preparedStatement.setString(6, user.getDataCriacao());
			preparedStatement.setInt(7, user.getId());
			preparedStatement.execute();

		} finally {

			if (preparedStatement != null && !preparedStatement.isClosed()) {
				preparedStatement.close();
			}

			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

	}

	public static void excluir(Integer id) throws SQLException, Exception {

		String sql = "DELETE FROM tb_usuario WHERE (ID_USUARIO=?)";

		Connection connection = null;

		PreparedStatement preparedStatement = null;

		try {

			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			

		} finally {

			if (preparedStatement != null && !preparedStatement.isClosed()) {
				preparedStatement.close();
			}

			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

	}

	public static List<User> listar() throws SQLException, Exception {

		String sql = "SELECT * FROM tb_usuario";

		List<User> listaUser = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		try {
			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);

			result = preparedStatement.executeQuery();

			while (result.next()) {

				if (listaUser == null) {
					listaUser = new ArrayList<User>();
				}

				User user = new User();
				user.setId(result.getInt("ID_USUARIO"));
				user.setNome(result.getString("NICK_USUARIO"));
				user.setNome(result.getString("NOME_USUARIO"));
				user.setEmail(result.getString("EMAIL_USUARIO"));
				user.setSenha(result.getString("SENHA_USUARIO"));
				user.setSaldo(result.getDouble("SALDO_USUARIO"));
				user.setDataCriacao(result.getString("DATA_CRIACAO_USUARIO"));
				listaUser.add(user);

			}

		} finally {

			if (result != null && !result.isClosed()) {
				result.close();
			}

			if (preparedStatement != null && !preparedStatement.isClosed()) {
				preparedStatement.close();
			}

			if (connection != null && !connection.isClosed()) {
				connection.close();
			}

		}

		return listaUser;

	}

}
