package br.senac.backend.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.senac.backend.db.utils.ConnectionUtils;
import br.senac.backend.models.Game;

public class DaoGame {

	public static Integer insert(Game game) throws SQLException, Exception {
		String sql = "INSERT INTO tb_jogo (NOME_JOGO, PRECO_JOGO, DATA_LANCAMENTO_JOGO, DESENVOLVEDOR_JOGO, DESCRICAO_JOGO, ID_TB_CATEGORIA, ENABLED) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, game.getNome());
			preparedStatement.setDouble(2, game.getPreco());
			preparedStatement.setString(3, game.getDataLancamento());
			preparedStatement.setString(4, game.getDesenvolvedor());
			preparedStatement.setString(5, game.getDescricao());
			preparedStatement.setInt(6, game.getIdCategoria());
			preparedStatement.setBoolean(7, true);
			preparedStatement.execute();
			
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if(rs.next())
			  return rs.getInt(1);
			
			return null;
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
		String sql = "UPDATE tb_jogo SET NOME_JOGO=?, PRECO_JOGO=?, DATA_LANCAMENTO_JOGO=?, "
				+ "DESENVOLVEDOR_JOGO=?, DESCRICAO_JOGO=?, ID_TB_CATEGORIA=?, ENABLED=?"
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
			preparedStatement.setBoolean(7, game.isEnabled());
			preparedStatement.setInt(8, game.getId());

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

	public static void updateByName(Game game) throws SQLException, Exception {
		String sql = "UPDATE tb_jogo SET NOME_JOGO=? , PRECO_JOGO=? , DATA_LANCAMENTO_JOGO=? , DESENVOLVEDOR_JOGO=? , DESCRICAO_JOGO=? , ID_TB_CATEGORIA=?"
				+ " , FULL_PATH=?, ENABLED=?"
				+ " WHERE (NOME_JOGO=?)";

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
			preparedStatement.setString(7, game.getFullPath());
			preparedStatement.setBoolean(8, game.isEnabled());
			preparedStatement.setString(9, game.getNome());

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
		String sql = "UPDATE tb_jogo SET ENABLED = FALSE WHERE (`ID_JOGO` = ?)";
				
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
		String sql = "SELECT * FROM tb_jogo WHERE ENABLED = '1'";
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

	public static List<Game> listAll() throws SQLException, Exception {
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

	public static List<Game> findAllByName(String nome) throws SQLException, Exception {
		String sql = "SELECT * FROM tb_jogo where NOME_JOGO LIKE ? AND ENABLED = '1'";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		List<Game> listaGames = null;
		try {
			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + nome + "%");
			result = preparedStatement.executeQuery();
			if (result != null && result.next()) {
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

	public static List<Game> findGamesByDesc(String descricao_jogo) throws SQLException, Exception {
		String sql = "SELECT * FROM tb_jogo where DESCRICAO_JOGO = ? AND ENABLED = '1'";

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
			System.out.println("Entrei");
			if (result != null && result.next()) {
				game.setId(result.getInt("ID_JOGO"));
				game.setNome(result.getString("NOME_JOGO"));
				game.setPreco(result.getDouble("PRECO_JOGO"));
				game.setDataLancamento(result.getString("DATA_LANCAMENTO_JOGO"));
				game.setDesenvolvedor(result.getString("DESENVOLVEDOR_JOGO"));
				game.setDescricao(result.getString("DESCRICAO_JOGO"));
				game.setIdCategoria(result.getInt("ID_TB_CATEGORIA"));
				game.setIdCategoria(result.getInt("ID_TB_CATEGORIA"));
				game.setEnabled(result.getBoolean("ENABLED"));
			}
		} catch (Exception e) {
			System.out.println("Deu ruim pegar o game: " + e);
		}
		
		return game;
	}

}