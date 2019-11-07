package br.senac.backend.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senac.backend.db.utils.ConnectionUtils;
import br.senac.backend.model.category.Category;

public class DaoCategory {

	public static void insertCategory(Category category) throws SQLException, Exception {

		String sql = "INSERT INTO tb_categoria (NOME_CATEGORIA)" + " VALUES (?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, category.getNome_categoria());
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

	public static void updateCategory(Category category) throws SQLException, Exception {

		String sql = "UPDATE tb_categoria SET NOME_CATEGORIA=? WHERE (ID_CATEGORIA=?)";

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {

			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, category.getNome_categoria());
			preparedStatement.setInt(2, category.getId());
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

	public static void deleteCategory(Integer id) throws SQLException, Exception {

		String sql = "DELETE FROM tb_categoria WHERE (ID_CATEGORIA=?)";

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

	public static List<Category> listCategory() throws SQLException, Exception {

		String sql = "SELECT * FROM tb_categoria";

		List<Category> listaCategory = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		try {
			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);

			result = preparedStatement.executeQuery();

			while (result.next()) {

				if (listaCategory == null) {
					listaCategory = new ArrayList<Category>();
				}

				Category category = new Category();
				category.setId(result.getInt("ID_CATEGORIA"));
				category.setNome_categoria(result.getString("NOME_CATEGORIA"));
				listaCategory.add(category);

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

		return listaCategory;

	}

	public static Category listCategoryByName(String category_name) throws SQLException, Exception {

		String sql = "SELECT * FROM tb_categoria where NOME_CATEGORIA = ?";

		Category category = new Category();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		try {
			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, category_name);
			result = preparedStatement.executeQuery();

			if (result != null && result.next()) {
				category.setId(result.getInt("ID_CATEGORIA"));
				category.setNome_categoria(result.getString("NOME_CATEGORIA"));
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

		return category;

	}

	public static Category findById(Integer id) throws SQLException, Exception {

		String sql = "SELECT * FROM tb_categoria where ID_CATEGORIA = ?";

		Category category = new Category();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		try {
			connection = ConnectionUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeQuery();
			if (result != null && result.next()) {
				category.setId(result.getInt("ID_CATEGORIA"));
				category.setNome_categoria(result.getString("NOME_CATEGORIA"));
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
		return category;
	}
}
