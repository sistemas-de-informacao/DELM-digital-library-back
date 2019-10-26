package br.senac.backend.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senac.backend.db.utils.ConnectionUtils;
import br.senac.backend.model.clientes.Cliente;

//Data Access Object de Cliente. Realiza opera��es de BD com o cliente. 
public class DaoCliente {

    //Insere um cliente na tabela "cliente" do banco de dados
    public static void inserir(Cliente cliente)
            throws SQLException, Exception {
    	
        //Monta a string de inser��o de um cliente no BD,
        //utilizando os dados do clientes passados como par�metro
        String sql = "INSERT INTO cliente (nome, cpf, enabled) "
                + " VALUES (?, ?, ?)";
        
        //Conex�o para abertura e fechamento
        Connection connection = null;
        //Statement para obten��o atrav�s da conex�o, execu��o de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        
        try {
        	
            //Abre uma conex�o com o banco de dados
            connection = ConnectionUtils.getConnection();
            
            //Cria um statement para execu��o de instru��es SQL
            preparedStatement = connection.prepareStatement(sql);
            
            //Configura os par�metros do "PreparedStatement"
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getCpf());
            preparedStatement.setBoolean(3, true);
            
            //Executa o comando no banco de dados
            preparedStatement.execute();
            
        } finally {
            
        	//Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            
            //Se a conex�o ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            
        }
        
    }

    //Realiza a atualiza��o dos dados de um cliente, com ID e dados
    //fornecidos como par�metro atrav�s de um objeto da classe "Cliente"
    public static void atualizar(Cliente cliente)
            throws SQLException, Exception {
        
    	//Monta a string de atualiza��o do cliente no BD, utilizando
        //prepared statement
        String sql = "UPDATE cliente SET nome=?, cpf=? "
            + "WHERE (cliente_id=?)";
        
        //Conex�o para abertura e fechamento
        Connection connection = null;
        //Statement para obten��o atrav�s da conex�o, execu��o de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        
        try {
        	
            //Abre uma conex�o com o banco de dados
            connection = ConnectionUtils.getConnection();
            
            //Cria um statement para execu��o de instru��es SQL
            preparedStatement = connection.prepareStatement(sql);
            
            //Configura os par�metros do "PreparedStatement"
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getCpf());
            preparedStatement.setInt(3, cliente.getId());
            
            //Executa o comando no banco de dados
            preparedStatement.execute();
            
        } finally {
        	
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            
            //Se a conex�o ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            
        }
        
    }

    //Realiza a exclus�o l�gica de um cliente no BD, com ID fornecido
    //como par�metro. A exclus�o l�gica simplesmente "desliga" o
    //cliente, configurando um atributo espec�fico, a ser ignorado
    //em todas as consultas de cliente ("enabled").
    public static void excluir(Integer id) throws SQLException, Exception {
    	
        //Monta a string de atualiza��o do cliente no BD, utilizando
        //prepared statement
        String sql = "UPDATE cliente SET enabled=? WHERE (cliente_id=?)";
        
        //Conex�o para abertura e fechamento
        Connection connection = null;
        //Statement para obten��o atrav�s da conex�o, execu��o de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        
        try {
        	
            //Abre uma conex�o com o banco de dados
            connection = ConnectionUtils.getConnection();
            
            //Cria um statement para execu��o de instru��es SQL
            preparedStatement = connection.prepareStatement(sql);
            
            //Configura os par�metros do "PreparedStatement"
            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, id);
            
            //Executa o comando no banco de dados
            preparedStatement.execute();
            
        } finally {
        	
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            
            //Se a conex�o ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            
        }
        
    }

    //Lista todos os clientes da tabela clientes
    public static List<Cliente> listar()
            throws SQLException, Exception {
    	
        //Monta a string de listagem de clientes no banco, considerando
        //apenas a coluna de ativa��o de clientes ("enabled")
        String sql = "SELECT * FROM cliente WHERE (enabled=?)";   
        
        //Lista de clientes de resultado
        List<Cliente> listaClientes = null;
        
        //Conex�o para abertura e fechamento
        Connection connection = null;
        //Statement para obten��o atrav�s da conex�o, execu��o de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        //Armazenar� os resultados do banco de dados
        ResultSet result = null;
        
        try {
            //Abre uma conex�o com o banco de dados
            connection = ConnectionUtils.getConnection();
            
            //Cria um statement para execu��o de instru��es SQL
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, true);
            
            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();
            
            //Itera por cada item do resultado
            while (result.next()) {
            	
                //Se a lista n�o foi inicializada, a inicializa
                if (listaClientes == null) {
                    listaClientes = new ArrayList<Cliente>();
                }
                
                //Cria uma inst�ncia de Cliente e popula com os valores do BD
                Cliente cliente = new Cliente();
                cliente.setId(result.getInt("cliente_id"));
                cliente.setNome(result.getString("nome"));
                cliente.setCpf(result.getString("cpf"));
                
                //Adiciona a inst�ncia na lista
                listaClientes.add(cliente);
                
            }
            
        } finally {
        	
            //Se o result ainda estiver aberto, realiza seu fechamento
            if (result != null && !result.isClosed()) {
                result.close();
            }
            
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            
            //Se a conex�o ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            
        }
        
        //Retorna a lista de clientes do banco de dados
        return listaClientes;
        
    }

    //Procura um cliente no banco de dados, de acordo com o nome
    //ou com o sobrenome, passado como par�metro
    public static List<Cliente> procurar(String valor)
            throws SQLException, Exception {
    	
        //Monta a string de consulta de clientes no banco, utilizando
        //o valor passado como par�metro para busca nas colunas de
        //nome ou sobrenome (atrav�s do "LIKE" e ignorando min�sculas
        //ou mai�sculas, atrav�s do "UPPER" aplicado � coluna e ao
        //par�metro). Al�m disso, tamb�m considera apenas os elementos
        //que possuem a coluna de ativa��o de clientes configurada com
        //o valor correto ("enabled" com "true")
        String sql = "SELECT * FROM cliente WHERE ((UPPER(nome) LIKE UPPER(?) "
            + "OR UPPER(cliente.sobrenome) LIKE UPPER(?)) AND enabled=?)";
        
        //Lista de clientes de resultado
        List<Cliente> listaClientes = null;
        
        //Conex�o para abertura e fechamento
        Connection connection = null;
        //Statement para obten��o atrav�s da conex�o, execu��o de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        //Armazenar� os resultados do banco de dados
        ResultSet result = null;
        
        try {
        	
            //Abre uma conex�o com o banco de dados
            connection = ConnectionUtils.getConnection();
            
            //Cria um statement para execu��o de instru��es SQL
            preparedStatement = connection.prepareStatement(sql);
            
            //Configura os par�metros do "PreparedStatement"
            preparedStatement.setString(1, "%" + valor + "%");
            preparedStatement.setString(2, "%" + valor + "%");
            preparedStatement.setBoolean(3, true);
            
            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();
            
            //Itera por cada item do resultado
            while (result.next()) {
            	
                //Se a lista n�o foi inicializada, a inicializa
                if (listaClientes == null) {
                    listaClientes = new ArrayList<Cliente>();
                }
                
                //Cria uma inst�ncia de Cliente e popula com os valores do BD
                Cliente cliente = new Cliente();
                cliente.setId(result.getInt("cliente_id"));
                cliente.setNome(result.getString("nome"));
                cliente.setCpf(result.getString("cpf"));
                
                //Adiciona a inst�ncia na lista
                listaClientes.add(cliente);
                
            }
            
        } finally {
        	
            //Se o result ainda estiver aberto, realiza seu fechamento
            if (result != null && !result.isClosed()) {
                result.close();
            }
            
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            
            //Se a conex�o ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            
        }
        
        //Retorna a lista de clientes do banco de dados
        return listaClientes;
        
    }

    //Obt�m uma inst�ncia da classe "Cliente" atrav�s de dados do
    //banco de dados, de acordo com o ID fornecido como par�metro
    public static Cliente obter(Integer id)
            throws SQLException, Exception {
    	
        //Comp�e uma String de consulta que considera apenas o cliente
        //com o ID informado e que esteja ativo ("enabled" com "true")
        String sql = "SELECT * FROM cliente WHERE (cliente_id=? AND enabled=?)";

        //Conex�o para abertura e fechamento
        Connection connection = null;
        //Statement para obten��o atrav�s da conex�o, execu��o de
        //comandos SQL e fechamentos
        PreparedStatement preparedStatement = null;
        //Armazenar� os resultados do banco de dados
        ResultSet result = null;
        
        try {
            //Abre uma conex�o com o banco de dados
            connection = ConnectionUtils.getConnection();
            
            //Cria um statement para execu��o de instru��es SQL
            preparedStatement = connection.prepareStatement(sql);
            
            //Configura os par�metros do "PreparedStatement"
            preparedStatement.setInt(1, id);            
            preparedStatement.setBoolean(2, true);
            
            //Executa a consulta SQL no banco de dados
            result = preparedStatement.executeQuery();
            
            //Verifica se h� pelo menos um resultado
            if (result.next()) {
            	
                //Cria uma inst�ncia de Cliente e popula com os valores do BD
                Cliente cliente = new Cliente();
                cliente.setId(result.getInt("cliente_id"));
                cliente.setNome(result.getString("nome"));
                cliente.setCpf(result.getString("cpf"));
                
                //Retorna o resultado
                return cliente;
                
            }
            
        } finally {
        	
            //Se o result ainda estiver aberto, realiza seu fechamento
            if (result != null && !result.isClosed()) {
                result.close();
            }
            
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            
            //Se a conex�o ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            
        }

        //Se chegamos aqui, o "return" anterior n�o foi executado porque
        //a pesquisa n�o teve resultados
        //Neste caso, n�o h� um elemento a retornar, ent�o retornamos "null"
        return null;
        
    }
    
}