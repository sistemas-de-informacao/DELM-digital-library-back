package br.senac.backend.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senac.backend.db.utils.ConnectionUtils;
import br.senac.backend.models.Game;

public class DaoGame {

	public static void insert(Game game) throws SQLException, Exception {
		String sql = "INSERT INTO tb_jogo (NOME_JOGO, PRECO_JOGO, DATA_LANCAMENTO_JOGO, DESENVOLVEDOR_JOGO, DESCRICAO_JOGO, ID_TB_CATEGORIA) "
				+ " VALUES (?, ?, ?, ?, ?, ?)";

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, game.getNome());
			preparedStatement.setDouble(2, game.getPreco());
			preparedStatement.setString(3, game.getDataLancamento());
			preparedStatement.setString(4, game.getDesenvolvedor());
			preparedStatement.setString(5, game.getDescricao());
			preparedStatement.setInt(6, game.getIdCategoria());
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

	public static void update(Game game) throws SQLException, Exception {
		String sql = "UPDATE tb_jogo SET NOME_JOGO=? , PRECO_JOGO=? , DATA_LANCAMENTO_JOGO=? , DESENVOLVEDOR_JOGO=? , DESCRICAO_JOGO=? , ID_TB_CATEGORIA=?"
				+ " WHERE (ID_JOGO=?)";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, game.getNome());
			preparedStatement.setDouble(2, game.getPreco());
			preparedStatement.setString(3, game.getDataLancamento());
			preparedStatement.setString(4, game.getDesenvolvedor());
			preparedStatement.setString(5, game.getDescricao());
			preparedStatement.setInt(6, game.getIdCategoria());
			preparedStatement.setInt(7, game.getId());

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

	public static void delete(Integer id) throws SQLException, Exception {
		String sql = "delete from tb_jogo WHERE (ID_JOGO=?)";
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

	public static List<Game> list() throws SQLException, Exception {
		String sql = "SELECT * FROM tb_jogo";
		List<Game> listaGames = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				if (listaGames == null) {
					listaGames = new ArrayList<Game>();
				}

				Game game = new Game();
				game.setId(result.getInt("ID_JOGO"));
				game.setNome(result.getString("NOME_JOGO"));
				game.setPreco(result.getDouble("PRECO_JOGO"));
				game.setDataLancamento(result.getString("DATA_LANCAMENTO_JOGO"));
				game.setDesenvolvedor(result.getString("DESENVOLVEDOR_JOGO"));
				game.setDescricao(result.getString("DESCRICAO_JOGO"));
				game.setIdCategoria(result.getInt("ID_TB_CATEGORIA"));
				listaGames.add(game);
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

	public static Game findByName(String nome) throws SQLException, Exception {
		String sql = "SELECT * FROM tb_jogo where NOME_JOGO = ?";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			connection = ConnectionUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nome);
			result = preparedStatement.executeQuery();
			if (result != null && result.next()) {
				Game game = new Game();

				game.setId(result.getInt("ID_JOGO"));
				game.setNome(result.getString("NOME_JOGO"));
				game.setPreco(result.getDouble("PRECO_JOGO"));
				game.setDataLancamento(result.getString("DATA_LANCAMENTO_JOGO"));
				game.setDesenvolvedor(result.getString("DESENVOLVEDOR_JOGO"));
				game.setDescricao(result.getString("DESCRICAO_JOGO"));
				game.setIdCategoria(result.getInt("ID_TB_CATEGORIA"));

				return game;
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

		return null;
	}

	public static List<Game> findGamesByDesc(String descricao_jogo) throws SQLException, Exception {
		String sql = "SELECT * FROM tb_jogo where DESCRICAO_JOGO = ?";

		List<Game> listaGames = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, descricao_jogo);

			result = preparedStatement.executeQuery();
			while (result.next()) {

				if (listaGames == null) {
					listaGames = new ArrayList<Game>();
				}

				Game game = new Game();
				game.setId(result.getInt("ID_JOGO"));
				game.setNome(result.getString("NOME_JOGO"));
				game.setPreco(result.getDouble("PRECO_JOGO"));
				game.setDataLancamento(result.getString("DATA_LANCAMENTO_JOGO"));
				game.setDesenvolvedor(result.getString("DESENVOLVEDOR_JOGO"));
				game.setDescricao(result.getString("DESCRICAO_JOGO"));
				game.setIdCategoria(result.getInt("ID_TB_CATEGORIA"));
				listaGames.add(game);
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

	public static Game findById(Integer id) throws SQLException, Exception {
		String sql = "SELECT * FROM tb_jogo where ID_JOGO = ?";
		Game game = new Game();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		try {
			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			result = preparedStatement.executeQuery();
			if (result != null && result.next()) {
				game.setId(result.getInt("ID_JOGO"));
				game.setNome(result.getString("NOME_JOGO"));
				game.setPreco(result.getDouble("PRECO_JOGO"));
				game.setDataLancamento(result.getString("DATA_LANCAMENTO_JOGO"));
				game.setDesenvolvedor(result.getString("DESENVOLVEDOR_JOGO"));
				game.setDescricao(result.getString("DESCRICAO_JOGO"));
				game.setIdCategoria(result.getInt("ID_TB_CATEGORIA"));
			}
		} catch (Exception e) {
			System.out.println("Deu ruim pegar o game: " + e);
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

		return game;
	}

}