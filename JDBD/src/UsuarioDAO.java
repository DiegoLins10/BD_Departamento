import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class UsuarioDAO {

	private Main conexao = new Main();
	private String sqlInsert;
	private String sqlUpdate;
	private String sqlDelete;
	private String sqlSelect;	
	private PreparedStatement st;
	private static Connection conn = null; 
    private ResultSet rs;
    
    UsuarioDAO () {
    	sqlInsert = "Insert Into Empregado (IdEmpregado, NomeEmpregado, IdDepto, Cargo, Tempo_Emp, Salario, Comissao) Values (?, ?, ?, ?, ?, ?, ?)";
		sqlUpdate = "Update Usuario Set Nome = ?, Email = ?, Telefone = ?, Endereco = ? Where IdUsuario = ?";
		sqlDelete = "Delete From Usuario Where IdUsuario = ?";
		sqlSelect = "Select IdUsuario, Nome, Email, Telefone, Endereco From Usuario";
	}
    
    public void insert(Empregado usuario){
		conn = conexao.conectar();
		try {
			st = conn.prepareStatement(sqlInsert);
			st.setInt(1, usuario.getIdEmpregado());
			st.setString(2, usuario.getNomeEmpregado());
			st.setInt(3, usuario.getIdDepto());
			st.setString(4, usuario.getCargo());
			st.setInt(5, usuario.getTempo_Emp());
			st.setDouble(6, usuario.getSalario());
			st.setDouble(7, usuario.getComissao());
			st.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro de execução comando \n" + sqlInsert+"\n"+e);
		}
		finally{
			conexao.fecharConexao(conn);
		}
	}

}
