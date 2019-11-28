package br.senac.backend.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.senac.backend.db.utils.ConnectionUtils;
import br.senac.backend.models.Historic;

public class DaoHistoric {

	
	public static void insert(Historic historic) throws SQLException, Exception {
		String sql = "INSERT INTO tb_historic (CODIGO_COMPRA,DATA_COMPRA,ID_TB_JOGO,ID_TB_USUARIO)" + " VALUES (?,?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, historic.getCodCompra());
			preparedStatement.setString(1, historic.getDataCompra());
			preparedStatement.setLong(1, historic.getIdJogo());
			preparedStatement.setLong(1, historic.getIdUsuario());

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

}
