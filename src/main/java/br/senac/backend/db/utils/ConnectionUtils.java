package br.senac.backend.db.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

//Obt�m um objeto de conex�o do banco de dados.
//Pode ser utilizado para abertura e fechamento de conex�es com o banco
public class ConnectionUtils {
	
	public static void main(String[] args) {
		
		try {
			
			ConnectionUtils.getConnection();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

    //Obt�m uma conex�o do banco de dados
    public static Connection getConnection() throws Exception {
    	
        //Conex�o para abertura e fechamento
        Connection connection = null;
        
        //S� tenta abrir uma conex�o se n�o existir ou estiver fechada            
        //Endere�o de conex�o com o banco de dados
        String dbURL = "jdbc:mysql://localhost:3306/pi";
        
        //Propriedades para armazenamento de usu�rio e senha
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "adminadmin");
        properties.put("serverTimezone", "UTC");
        
        //Realiza a conex�o com o banco
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(dbURL, properties);

        //Retorna a conex�o
        return connection;
    }
}
