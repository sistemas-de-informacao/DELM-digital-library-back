package br.senac.backend.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senac.backend.db.utils.ConnectionUtils;
import br.senac.backend.models.Game;

public class DaoLibrary {

	public static void insert(Integer jogo, Integer usuario) throws SQLException, Exception {
		String sql = "INSERT INTO tb_biblioteca (ID_TB_JOGO, ID_TB_USUARIO)" + " VALUES (?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, jogo);
			preparedStatement.setDouble(2, usuario);

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

	public static List<Game> findAllByUsuario(Integer id) throws SQLException, Exception {
		String sql = "SELECT * FROM tb_biblioteca where id_tb_usuario = ?";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		List<Game> listaGames = null;
		try {
			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeQuery();
			if (result != null && result.next()) {
				if (listaGames == null) {
					listaGames = new ArrayList<Game>();
				}

				listaGames.add(DaoGame.findById(result.getInt("ID_TB_JOGO")));
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

		return listaGames;
	}

	public static boolean findByUsuarioAndJogo(Integer game, Integer usuario) throws SQLException, Exception {
		String sql = "SELECT * FROM tb_biblioteca where id_tb_jogo = ? and id_tb_usuario = ?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		try {
			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, game);
			preparedStatement.setInt(2, usuario);
			result = preparedStatement.executeQuery();

			if (result != null && result.next()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("Erro ao executar query de busca para verificar se usuário tem jogo \n" + "Erro: " + e.getMessage());
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

		return false;
	}

}
