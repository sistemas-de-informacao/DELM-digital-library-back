package br.senac.backend.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import br.senac.backend.db.utils.ConnectionUtils;
import br.senac.backend.db.utils.ResponseUtils;
import br.senac.backend.dto.UpdatePasswordDTO;
import br.senac.backend.models.Permissoes;
import br.senac.backend.models.User;

public class DaoUser {

	public static void inserir(User user) throws SQLException, Exception {
		String sql = "INSERT INTO tb_usuario (NICK_USUARIO, NOME_USUARIO, EMAIL_USUARIO, SENHA_USUARIO, SALDO_USUARIO, DATA_CRIACAO_USUARIO, ENABLE_USUARIO, TIPO_CONTA_USUARIO)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
			preparedStatement.setBoolean(7, true);
			preparedStatement.setInt(8, user.getTipo().ordinal());
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
		String sql = "UPDATE tb_usuario SET NICK_USUARIO=?, NOME_USUARIO=?,EMAIL_USUARIO=?, SALDO_USUARIO=?, DATA_CRIACAO_USUARIO=?, ENABLE_USUARIO=?, TIPO_CONTA_USUARIO=? "
				+ "WHERE (ID_USUARIO=?)";

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, user.getNickname());
			preparedStatement.setString(2, user.getNome());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setDouble(4, user.getSaldo());
			preparedStatement.setString(5, user.getDataCriacao());
			preparedStatement.setBoolean(6, user.getEnable());
			preparedStatement.setInt(7, user.getTipo().ordinal());
			preparedStatement.setInt(8, user.getId());
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
		String sql = "SELECT * FROM tb_usuario WHERE (ENABLE_USUARIO)=1";

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
				user.setNickname(result.getString("NICK_USUARIO"));
				user.setNome(result.getString("NOME_USUARIO"));
				user.setEmail(result.getString("EMAIL_USUARIO"));
				user.setSenha(result.getString("SENHA_USUARIO"));
				user.setSaldo(result.getDouble("SALDO_USUARIO"));
				user.setDataCriacao(result.getString("DATA_CRIACAO_USUARIO"));
				user.setEnable(result.getBoolean("ENABLE_USUARIO"));
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

	public static User findByNickname(String nickname) throws SQLException, Exception {
		String sql = "SELECT * FROM tb_usuario where NICK_USUARIO LIKE ?";

		User user = new User();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		try {
			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nickname);
			result = preparedStatement.executeQuery();

			if (result != null && result.next()) {
				user.setId(result.getInt("ID_USUARIO"));
				user.setNickname(result.getString("NICK_USUARIO"));
				user.setNome(result.getString("NOME_USUARIO"));
				user.setEmail(result.getString("EMAIL_USUARIO"));
				user.setSenha(result.getString("SENHA_USUARIO"));
				user.setSaldo(result.getDouble("SALDO_USUARIO"));
				user.setDataCriacao(result.getString("DATA_CRIACAO_USUARIO"));
				user.setEnable(result.getBoolean("ENABLE_USUARIO"));
				user.setTipo(Permissoes.getPermissao(result.getInt("TIPO_CONTA_USUARIO")));

				return user;
			}
		} catch (Exception e) {
			System.out.println("Erro ao executar query de busca para login \n" + "Erro: " + e.getMessage());
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

	public static User findByEmail(String email_user) throws SQLException, Exception {
		String sql = "SELECT * FROM tb_usuario where EMAIL_USUARIO like ?";

		User user = new User();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email_user);
			result = preparedStatement.executeQuery();

			if (result != null && result.next()) {
				user.setId(result.getInt("ID_USUARIO"));
				user.setNome(result.getString("NICK_USUARIO"));
				user.setNome(result.getString("NOME_USUARIO"));
				user.setEmail(result.getString("EMAIL_USUARIO"));
				user.setSenha(result.getString("SENHA_USUARIO"));
				user.setSaldo(result.getDouble("SALDO_USUARIO"));
				user.setDataCriacao(result.getString("DATA_CRIACAO_USUARIO"));
				user.setEnable(result.getBoolean("ENABLE_USUARIO"));
				user.setTipo(Permissoes.getPermissao(result.getInt("TIPO_CONTA_USUARIO")));
				
				return user;
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

	public static User findById(Integer id) throws SQLException, Exception {
		String sql = "SELECT * FROM tb_usuario where ID_USUARIO = ?";

		User user = new User();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeQuery();

			if (result != null && result.next()) {
				user.setId(result.getInt("ID_USUARIO"));
				user.setNickname(result.getString("NICK_USUARIO"));
				user.setNome(result.getString("NOME_USUARIO"));
				user.setEmail(result.getString("EMAIL_USUARIO"));
				user.setSenha(result.getString("SENHA_USUARIO"));
				user.setSaldo(result.getDouble("SALDO_USUARIO"));
				user.setDataCriacao(result.getString("DATA_CRIACAO_USUARIO"));
				user.setEnable(result.getBoolean("ENABLE_USUARIO"));
				user.setTipo(Permissoes.getPermissao(result.getInt("ENABLE_USUARIO")));
			} else {
				System.out.println("Deu ruim na hora de buscar o usuario pelo ID");
			}
		} catch (Exception e) {
			System.out.println("Deu ruim na hora de buscar o usuario pelo ID ERRO => " + e);
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

		return user;
	}
	
	public static List<User> findAllByNickname(String nickname) throws SQLException, Exception {
		String sql = "SELECT * FROM tb_usuario where NICK_USUARIO LIKE ? and ENABLE_USUARIO = 1";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;

		List<User> listaUsuarios = null;
		try {
			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "%" + nickname + "%");
			result = preparedStatement.executeQuery();
			System.out.println(result);
			if (result != null && result.next()) {
				if (listaUsuarios == null) {
					listaUsuarios = new ArrayList<User>();
				}
				User user = new User();

				user.setId(result.getInt("ID_USUARIO"));
				user.setNickname(result.getString("NICK_USUARIO"));
				user.setNome(result.getString("NOME_USUARIO"));
				user.setEmail(result.getString("EMAIL_USUARIO"));
				user.setSenha(result.getString("SENHA_USUARIO"));
				user.setSaldo(result.getDouble("SALDO_USUARIO"));
				user.setDataCriacao(result.getString("DATA_CRIACAO_USUARIO"));
				user.setEnable(result.getBoolean("ENABLE_USUARIO"));
				user.setTipo(Permissoes.getPermissao(result.getInt("ENABLE_USUARIO")));

				listaUsuarios.add(user);
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

		return listaUsuarios;
	}

	public static String ativar(String nickname) throws SQLException, Exception {
		String sql = "UPDATE tb_usuario SET ENABLE_USUARIO=1 "
				+ "WHERE (NICK_USUARIO=?)";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, nickname);
			preparedStatement.executeUpdate();
			
			return nickname;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
	}
	
	public static Response updatePassword(UpdatePasswordDTO senhas, Integer id) throws SQLException, Exception {
		String sql = "UPDATE tb_usuario SET SENHA_USUARIO=? "
				+ "WHERE (ID_USUARIO=?)";

		if (DaoUser.findById(id) == null)
			return ResponseUtils.successReturnString(Response.Status.OK, "Usuário não encontrado");
		
		User usuario = DaoUser.findById(id);
		
		if (!usuario.getSenha().equals(senhas.getSenhaAntiga()))
			return ResponseUtils.successReturnString(Response.Status.OK, "A senha antiga está incorreta");
			
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			connection = ConnectionUtils.getConnection();

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, senhas.getSenhaNova());
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
	}
	
}
