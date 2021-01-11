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
	private String sqlSelectIdEmpregado;
	private String sqlSelectNomeEmpregado;
	private String sqlSelectIdDepto;
	private PreparedStatement st;
	private static Connection conn = null; 
    private ResultSet rs;
    
    UsuarioDAO () {
    	sqlInsert = "Insert Into Empregado (IdEmpregado, NomeEmpregado, IdDepto, Cargo, Tempo_Emp, Salario, Comissao) Values (?, ?, ?, ?, ?, ?, ?)";
		sqlUpdate = "Update Empregado Set NomeEmpregado = ?, IdDepto = ?, Cargo = ?, Tempo_Emp = ?, Salario = ?, Comissao = ? Where IdEmpregado = ?";
		sqlDelete = "Delete From Usuario Where IdUsuario = ?";
		sqlSelect = "Select IdEmpregado, NomeEmpregado, IdDepto, Cargo, Tempo_Emp, Salario, Comissao From Empregado";
		sqlSelectIdEmpregado = sqlSelect + " Where IdEmpregado = ?"; 
		sqlSelectNomeEmpregado = sqlSelect + " Where NomeEmpregado Like ?";
		sqlSelectIdDepto = sqlSelect + " Where IdDepto Like ?";
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
    public void update(Empregado usuario){
		conn = conexao.conectar();
		try {
			st = conn.prepareStatement(sqlUpdate);
			st.setString(1, usuario.getNomeEmpregado());
			st.setInt(2, usuario.getIdDepto());
			st.setString(3, usuario.getCargo());
			st.setInt(4, usuario.getTempo_Emp());
			st.setDouble(5, usuario.getSalario());
			st.setDouble(6, usuario.getComissao());
			st.setInt(7, usuario.getIdEmpregado());
			st.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro de execução comando \n" + sqlUpdate+"\n"+e);
		}
		finally{
			conexao.fecharConexao(conn);
		}
	}
    
    public Empregado select(int idUsuario) {
		conn = conexao.conectar();
		Empregado usuario = new Empregado();

		try {
			st = conn.prepareStatement(sqlSelectIdEmpregado);
			st.setInt(1, idUsuario);
			System.out.println(sqlSelectIdEmpregado);
			rs = st.executeQuery();
			if (rs.next()) {
				usuario.setIdEmpregado(rs.getInt("idEmpregado"));
				usuario.setNomeEmpregado(rs.getString("NomeEmpregado"));
				usuario.setIdDepto(Integer.parseInt(rs.getString("IdDepto")));
				usuario.setCargo(rs.getString("Cargo"));
				usuario.setTempo_Emp(Integer.parseInt(rs.getString("Tempo_Emp")));
				usuario.setSalario(Double.parseDouble(rs.getString("Salario")));
				usuario.setComissao(Double.parseDouble(rs.getString("Comissao")));
			} else {
				usuario = null;
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro de execução comando \n" + sqlSelectIdEmpregado+"\n"+e);
		}
		finally{
			conexao.fecharConexao(conn);
			return usuario;
		}
	}
    
    public Empregado select(String texto, String campo) {  // CONEXAO USADA PARA O CADASTRO NA INTERFACE. //
		texto +="%";
		conn = conexao.conectar();
		Empregado usuario = new Empregado();
		String sql="";

		if (campo.equals("NomeEmpregado"))
			sql = sqlSelectNomeEmpregado;
		else
			sql = sqlSelectIdEmpregado; 
		
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, texto);
			rs = st.executeQuery();
			if (rs.next()) {
				usuario.setIdEmpregado(rs.getInt("idEmpregado"));
				usuario.setNomeEmpregado(rs.getString("NomeEmpregado"));
				usuario.setIdDepto(Integer.parseInt(rs.getString("IdDepto")));
				usuario.setCargo(rs.getString("Cargo"));
				usuario.setTempo_Emp(Integer.parseInt(rs.getString("Tempo_Emp")));
				usuario.setSalario(Double.parseDouble(rs.getString("Salario")));
				usuario.setComissao(Double.parseDouble(rs.getString("Comissao")));
			} else {
				usuario = null;
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro de execução comando \n" + sql +"\n"+e);
		}
		finally{
			conexao.fecharConexao(conn);
			return usuario;
		}
	}
    public void delete(int IdUsuario){
		conn = conexao.conectar();
		try {
			st = conn.prepareStatement(sqlDelete);
			st.setInt(1, IdUsuario);
			st.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro de execução comando \n" + sqlDelete+"\n"+e);
		}
		finally{
			conexao.fecharConexao(conn);
		}
	}

}
