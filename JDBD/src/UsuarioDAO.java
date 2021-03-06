import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Classe que tem os metodos para fazer as chamadas para o banco de dados
 * @author Diego Lins
 * @version 11/01/2020
 */

public class UsuarioDAO {

	private Conexao conexao = new Conexao();
	private String sqlInsert;
	private String sqlUpdate;
	private String sqlDelete;
	private String sqlSelect;
	private String sqlSelectIdEmpregado;
	private String sqlSelectNomeEmpregado;
	private String sqlSelectCargo;
	private PreparedStatement st;
	private static Connection conn = null;
	private ResultSet rs;

	UsuarioDAO() {
		sqlInsert = "Insert Into Empregado (IdEmpregado, NomeEmpregado, IdDepto, Cargo, Tempo_Emp, Salario, Comissao) Values (?, ?, ?, ?, ?, ?, ?)";
		sqlUpdate = "Update Empregado Set NomeEmpregado = ?, IdDepto = ?, Cargo = ?, Tempo_Emp = ?, Salario = ?, Comissao = ? Where IdEmpregado = ?";
		sqlDelete = "Delete From Empregado Where IdEmpregado = ?";
		sqlSelect = "Select IdEmpregado, NomeEmpregado, IdDepto, Cargo, Tempo_Emp, Salario, Comissao From Empregado";
		sqlSelectIdEmpregado = sqlSelect + " Where IdEmpregado = ?";
		sqlSelectNomeEmpregado = sqlSelect + " Where NomeEmpregado Like ?";
		sqlSelectCargo = sqlSelect + " Where Cargo Like ?";
	}

	public void insert(Empregado usuario) {
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
			JOptionPane.showMessageDialog(null, "Erro de execu��o comando \n" + sqlInsert + "\n" + e);
		} finally {
			conexao.fecharConexao(conn);
		}
	}

	public void update(Empregado usuario) {
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
			JOptionPane.showMessageDialog(null, "Erro de execu��o comando \n" + sqlUpdate + "\n" + e);
		} finally {
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
			JOptionPane.showMessageDialog(null, "Erro de execu��o comando \n" + sqlSelectIdEmpregado + "\n" + e);
		} finally {
			conexao.fecharConexao(conn);
			return usuario;
		}
	}

	public Empregado select2(String texto, String campo) { // CONEXAO USADA PARA O CADASTRO NA INTERFACE. //
		texto += "%";
		conn = conexao.conectar();
		Empregado usuario = new Empregado();
		String sql = "";

		if (campo.equals("NomeEmpregado"))
			sql = sqlSelectNomeEmpregado;
		else
			sql = sqlSelectCargo;

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
			JOptionPane.showMessageDialog(null, "Erro de execu��o comando \n" + sql + "\n" + e);
		} finally {
			conexao.fecharConexao(conn);
			return usuario;
		}
	}

	public void delete(int IdUsuario) {
		conn = conexao.conectar();
		try {
			st = conn.prepareStatement(sqlDelete);
			st.setInt(1, IdUsuario);
			st.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro de execu��o comando \n" + sqlDelete + "\n" + e);
		} finally {
			conexao.fecharConexao(conn);
		}
	}

	public ArrayList<Empregado> getUsuarios() {   //cria um arrayList com todos os dados do BD
		conn = conexao.conectar();
		ArrayList<Empregado> usuarios = new ArrayList<Empregado>();

		try {
			st = conn.prepareStatement(sqlSelect);
			rs = st.executeQuery();
			while (rs.next()) {
				Empregado usuario = new Empregado();
				usuario.setIdEmpregado(rs.getInt("IdEmpregado"));
				usuario.setNomeEmpregado(rs.getString("NomeEmpregado"));
				usuario.setIdDepto(rs.getInt("IdDepto"));
				usuario.setCargo(rs.getString("Cargo"));
				usuario.setTempo_Emp(rs.getInt("Tempo_Emp"));
				usuario.setSalario(rs.getDouble("Salario"));
				usuario.setComissao(rs.getDouble("Comissao"));
				usuarios.add(usuario);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro de execu��o comando \n" + sqlSelect + "\n" + e);
		} finally {
			conexao.fecharConexao(conn);
			return usuarios;
		}
	}
}
