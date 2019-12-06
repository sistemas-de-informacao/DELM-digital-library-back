package br.senac.backend.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import br.senac.backend.db.utils.ConnectionUtils;
import br.senac.backend.dto.HistoricForm;
import br.senac.backend.models.Cart;
import br.senac.backend.models.Game;
import br.senac.backend.servicos.BuyService;

public class DaoBuy {

	public static Integer insert(Cart cart) throws SQLException, Exception {
		String sql = "INSERT INTO tb_compra (CODIGO_COMPRA, DATA_COMPRA, TOTAL_COMPRA, ID_TB_USUARIO)"
				+ " VALUES (?,?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, BuyService.gerarCodidoCompra());
			preparedStatement.setString(2, cart.getDataCompra());
			preparedStatement.setDouble(3, cart.getTotalCompra());
			preparedStatement.setLong(4, cart.getComprador().getId());

			preparedStatement.execute();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next())
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

	public static void insertJogoComprado(Integer id, Game jogo) throws SQLException, Exception {
		String sql = "INSERT INTO tb_jogo_compra (ID_TB_JOGO, ID_TB_COMPRA, PRECO_JOGO)" + " VALUES (?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, jogo.getId());
			preparedStatement.setDouble(2, id);
			preparedStatement.setDouble(3, jogo.getPreco());

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
	
	public static List<HistoricForm> listHistoricByUser(Integer id, Integer codigo) throws SQLException, Exception {
		String sql = "SELECT tb_jogo_compra.PRECO_JOGO, tb_jogo.NOME_JOGO, tb_compra.CODIGO_COMPRA, "
				+ "tb_compra.DATA_COMPRA, tb_compra.TOTAL_COMPRA " + 
				"FROM tb_jogo_compra " + 
				"INNER JOIN tb_jogo on (tb_jogo_compra.ID_TB_JOGO = tb_jogo.ID_JOGO) " + 
				"INNER JOIN tb_compra on (tb_compra.ID_TB_COMPRA = tb_jogo_compra.ID_TB_COMPRA) "
				+ "where tb_compra.ID_TB_USUARIO = ? and  tb_compra.CODIGO_COMPRA = ?";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		List<HistoricForm> listaHistorico = null;
		try {
			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, codigo);
			result = preparedStatement.executeQuery();
			
			while (result != null && result.next()) {
				if (listaHistorico == null) {
					listaHistorico = new ArrayList<HistoricForm>();
				}

				HistoricForm historico = new HistoricForm();

				historico.setPrecoJogo(result.getDouble("PRECO_JOGO"));
				historico.setJogoNome(result.getString("NOME_JOGO"));
				historico.setCodigoCompra(result.getInt("CODIGO_COMPRA"));
				historico.setDataCompra(result.getString("DATA_COMPRA"));
				historico.setTotalCompra(result.getDouble("TOTAL_COMPRA"));

				listaHistorico.add(historico);
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

		return listaHistorico;
	}
	
	public static HashMap<Integer, ArrayList<HistoricForm>> getAllCodigosByUser(Integer id) throws SQLException, Exception {
		String sql = "SELECT tb_compra.CODIGO_COMPRA " + 
				"FROM tb_compra where tb_compra.ID_TB_USUARIO = ?";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		HashMap<Integer, ArrayList<HistoricForm>> historicsForm = null;
		try {
			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeQuery();
			
			while (result != null && result.next()) {
				if (historicsForm == null) {
					historicsForm = new HashMap<Integer, ArrayList<HistoricForm>>();
				}
				
				historicsForm.put((Integer) result.getInt("CODIGO_COMPRA"),  (ArrayList<HistoricForm>) DaoBuy.listHistoricByUser(id, result.getInt("CODIGO_COMPRA")));
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
		
		return historicsForm;
	}

}
