import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Classe que faz a conexao com o banco de dados sql
 * @author Diego Lins
 * @version 11/01/2020
 */
public class Conexao {
	public Connection conectar () {
    // string de conexão...usando Windows Authentication
    String connectionUrl = "jdbc:sqlserver://localhost:1433;" +
      "databaseName=Departamento;integratedSecurity=true;";
 
    try {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
      Connection conn = DriverManager.getConnection(connectionUrl);
      System.out.println("Conexão obtida com sucesso.");
      return conn;
    }
    catch (SQLException ex) {
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
      return null;
    }
    catch (Exception e) {
      System.out.println("Problemas ao tentar conectar com o banco de dados: " + e);
      return null;
    }
  }
	public void fecharConexao (Connection conn){
		try {
			conn.close();
			System.out.println("Conexao "+ conn + "  fechada com sucesso.");
		}
		catch(SQLException ex) {
			System.out.println("Problemas na conexao com a fonte de dados   "+ ex);
		}
	}
}