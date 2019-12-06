package br.senac.backend.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senac.backend.db.utils.ConnectionUtils;
import br.senac.backend.dto.CategoryCountGraph;
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
		String sql = "SELECT * " + 
				"FROM tb_jogo " + 
				"INNER JOIN tb_biblioteca " + 
				"ON tb_jogo.ID_JOGO = tb_biblioteca.ID_TB_JOGO where tb_biblioteca.ID_TB_USUARIO = ?";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		List<Game> listaGames = null;
		try {
			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeQuery();
			
			while (result != null && result.next()) {
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

				System.out.println("entrei");
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

	
	public static List<Game> findAllByUser(Integer id) throws SQLException, Exception {
		String sql = "SELECT * " + 
				"FROM tb_jogo " + 
				"INNER JOIN tb_jogo_compra " + 
				"ON tb_jogo.ID_JOGO = tb_jogo_compra.ID_TB_JOGO where tb_jogo_compra.ID_TB_USUARIO = ?";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		List<Game> listaGames = null;
		try {
			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeQuery();
			
			while (result != null && result.next()) {
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

				System.out.println("entrei");
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
	
	public static List<CategoryCountGraph> countCategoriasJogosByUsuario(Integer id) throws SQLException, Exception {
		String sql = "select distinct qtd.qtd, c.id_tb_usuario, cat.nome_categoria from tb_jogo_compra cg " + 
				"inner join tb_jogo j on (cg.id_tb_jogo = j.id_jogo) " + 
				"left join (select count(id_jogo) qtd, id_tb_categoria from tb_jogo " + 
				"group by id_tb_categoria) qtd on (qtd.id_tb_categoria = j.id_tb_categoria) " + 
				"inner join tb_compra c on (c.id_tb_compra = cg.id_tb_compra) " + 
				"inner join tb_categoria cat on (cat.id_categoria = j.id_tb_categoria) where c.id_tb_usuario = ?";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		List<CategoryCountGraph> categoriesCountGraph = null;
		try {
			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeQuery();
			
			while (result != null && result.next()) {
				if (categoriesCountGraph == null) {
					categoriesCountGraph = new ArrayList<CategoryCountGraph>();
				}

				CategoryCountGraph categoryCountGraph = new CategoryCountGraph();

				categoryCountGraph.setCategory(result.getString("nome_categoria"));
				categoryCountGraph.setQtd(result.getInt("qtd"));

				categoriesCountGraph.add(categoryCountGraph);
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

		return categoriesCountGraph;
	}
}
