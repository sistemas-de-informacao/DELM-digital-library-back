package br.senac.backend.db.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senac.backend.db.utils.ConnectionUtils;
import br.senac.backend.model.games.Game;

public class DaoGame {

	public static void inserir(Game game) throws SQLException, Exception {

		String sql = "INSERT INTO delm_digital_library_db.tb_jogo (NOME_JOGO, PRECO_JOGO, DATA_LANCAMENTO, DESENVOLVEDOR_JOGO, DESCRICAO_JOGO, ID_TB_CATEGORIA) "
				+ " VALUES (?, ?, ?, ?, ?, ?)";

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, game.getNome());
			preparedStatement.setDouble(2, game.getPreco());
			preparedStatement.setDate(3, (Date) game.getDataLancamento());
			preparedStatement.setString(4, game.getDesenvolvedor());
			preparedStatement.setString(5, game.getDescricao());
			preparedStatement.setInt(6, game.getIdCategoria());
			preparedStatement.execute();
			System.out.println("Cadastro concluído com sucesso");
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

		String sql = "UPDATE delm_digital_library_db.tb_jogo SET NOME_JOGO=? , PRECO_JOGO=? , DATA_LANCAMENTO=? , DESENVOLVEDOR_JOGO=? , DESCRICAO_JOGO=? , ID_TB_CATEGORIA=?"
				+ "WHERE (ID_JOGO=?)";

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, game.getNome());
			preparedStatement.setDouble(2, game.getPreco());
			preparedStatement.setDate(3, (Date) game.getDataLancamento());
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

	public static void excluir(Integer id) throws SQLException, Exception {

		String sql = "UPDATE delm_digital_library_db.tb_jogo WHERE (ID_JOGO=?)";

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

	public static List<Game> listarGames() throws SQLException, Exception {

		String sql = "SELECT * FROM delm_digital_library_db.tb_jogo";

		List<Game> listaGames = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		try {
			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setBoolean(1, true);

			result = preparedStatement.executeQuery();

			while (result.next()) {

				if (listaGames == null) {
					listaGames = new ArrayList<Game>();
				}

				Game game = new Game();
				game.setId(result.getInt("ID_JOGO"));
				game.setNome(result.getString("NOME_JOGO"));
				game.setPreco(result.getDouble("PRECO_JOGO"));
				game.setDataLancamento(result.getDate("DATA_LANCAMENTO_JOGO"));
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
}