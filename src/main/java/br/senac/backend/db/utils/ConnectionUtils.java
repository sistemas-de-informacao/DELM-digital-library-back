package br.senac.backend.db.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionUtils {
	
	public static void main(String[] args) {
		
		try {
			
			ConnectionUtils.getConnection();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

    public static Connection getConnection() throws Exception {
        Connection connection = null;
        
        String dbURL = "jdbc:mysql://localhost:3306/delm_digital_db";
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "");
        properties.put("serverTimezone", "UTC");
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(dbURL, properties);
        return connection;
    }
}
