package br.senac.backend.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.senac.backend.db.utils.ConnectionUtils;
import br.senac.backend.models.Cart;
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

}
