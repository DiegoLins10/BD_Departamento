import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class CadastroDAO extends JFrame implements ActionListener {

	JLabel lblIdEmpregado, lblNomeEmpregado, lblIdDepto, lblCargo, lblTempo_Emp, lblSalario, lblComissao;
	JTextField txtIdEmpregado, txtNomeEmpregado, txtIdDepto, txtCargo, txtTempo_Emp, txtSalario, txtComissao;
	JPanel painel2, painel3;
	JButton btSalvar, btPesquisar, btLimpar, btSair, btExcluir;
	static CadastroDAO t01;
	
	CadastroDAO(){
				
		this.setTitle("Cadastro Usuários");
		this.setSize(500, 300);  // muda o tamanho da interface
		this.setResizable(false);
		this.getContentPane().setBackground(Color.WHITE);
		this.setLayout(new BorderLayout(5,10));
		
		btPesquisar = new JButton("Pesquisar");
		btLimpar = new JButton("Limpar"); 
		btSalvar = new JButton("Salvar");
		btExcluir = new JButton("Excluir");		
		btSair  = new JButton("Sair");

		painel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		painel2.add(btPesquisar);
		painel2.add(btLimpar);
		painel2.add(btSalvar);
		painel2.add(btExcluir);
		painel2.add(btSair);
		
		painel3 = new JPanel(new GridLayout(7,2,10,5)); // escolhe as colunas
		lblIdEmpregado = new JLabel("Id Empregado");
		lblNomeEmpregado = new JLabel("Nome empregado");
		lblIdDepto = new JLabel("Id departamento");
		lblCargo = new JLabel("Cargo");
		lblTempo_Emp = new JLabel("Tempo de empresa");
		lblSalario = new JLabel("Salario");
		lblComissao = new JLabel("Comissão");
		
		txtIdEmpregado = new JTextField();
		txtNomeEmpregado = new JTextField();
		txtIdDepto = new JTextField();
		txtCargo = new JTextField();
		txtTempo_Emp = new JTextField();
		txtSalario = new JTextField();
		txtComissao = new JTextField();
		
		painel3.add(lblIdEmpregado);
		painel3.add(txtIdEmpregado);
		
		painel3.add(lblNomeEmpregado);
		painel3.add(txtNomeEmpregado);

		painel3.add(lblIdDepto);
		painel3.add(txtIdDepto);

		painel3.add(lblCargo);
		painel3.add(txtCargo);

		painel3.add(lblTempo_Emp);
		painel3.add(txtTempo_Emp);
		
		painel3.add(lblSalario);
		painel3.add(txtSalario);
		
		painel3.add(lblComissao);
		painel3.add(txtComissao);
		painel3.setBackground(Color.WHITE);

		btSalvar.addActionListener(this);
		btLimpar.addActionListener(this);
		btPesquisar.addActionListener(this);
		btExcluir.addActionListener(this);
		
		btSair.addActionListener(this);
		
		this.add(painel2, BorderLayout.SOUTH);
		this.add(painel3, BorderLayout.CENTER);
		this.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		t01 = new CadastroDAO();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btSair) {
			System.exit(0);
		} if (e.getSource() == btPesquisar) {
			if (!(txtIdEmpregado.getText().isEmpty())) {
				pesquisar(txtIdEmpregado.getText(),"IdEmpregado",true);
			} else if (!(txtNomeEmpregado.getText().isEmpty())){
				pesquisar(txtNomeEmpregado.getText(),"NomeEmpregado",true);				
			} else if (!(txtIdDepto.getText().isEmpty())){
				pesquisar(txtIdDepto.getText(),"IdDepto",true);								
			} else {
				JOptionPane.showMessageDialog(null, "Por favor, \nDigite o Código, Nome ou o IdDepto !!!");
			}
		} else if (e.getSource() == btLimpar) {
			this.limpar();	
		} else if (e.getSource() == btExcluir) {
			int id = Integer.valueOf(txtIdEmpregado.getText());
			this.excluir(id);
		} else if (e.getSource() == btSalvar) {
			this.salvar();
		}
	}

	public boolean pesquisar(String texto, String campo, boolean atualizaTela){
		boolean flagEncontrado=false;
		Empregado usuario = new Empregado();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (campo.equals("IdEmpregado")) {
			usuario = usuarioDAO.select(Integer.parseInt(texto));    // Consultar usuário pelo Id
		} else  {
			usuario = usuarioDAO.select(campo, texto);    // Consultar usuário pelo Nome/Email		
		} 
	
		System.out.println(usuario);
		if (!(usuario == null)){
			if (atualizaTela) {
				txtIdEmpregado.setText(String.valueOf(usuario.getIdEmpregado()));
				txtNomeEmpregado.setText(usuario.getNomeEmpregado());
				txtIdDepto.setText(String.valueOf(usuario.getIdDepto()));
				txtCargo.setText(usuario.getCargo());
				txtTempo_Emp.setText(String.valueOf(usuario.getTempo_Emp()));
				txtSalario.setText(String.valueOf(usuario.getSalario()));
				txtComissao.setText(String.valueOf(usuario.getComissao()));
			}
			flagEncontrado = true;
		} else if (atualizaTela) {
			JOptionPane.showMessageDialog(null, "Usuário não encontrado !!!");
		}			
		return flagEncontrado;
	}
	
	public void limpar(){
		txtIdEmpregado.setText(null);
		txtNomeEmpregado.setText(null);
		txtIdDepto.setText(null);
		txtCargo.setText(null);
		txtTempo_Emp.setText(null);
		txtSalario.setText(null);
		txtComissao.setText(null);
	}
	
	public void salvar(){
		Empregado usuario = new Empregado();
		UsuarioDAO usuarioDAO = new UsuarioDAO();		
		
		if (this.pesquisar(txtIdEmpregado.getText(), "IdUsuario", false)) {
			usuario.setIdEmpregado(Integer.parseInt(txtIdEmpregado.getText()));
			usuario.setNomeEmpregado(txtNomeEmpregado.getText());
			usuario.setIdDepto(Integer.parseInt(txtIdDepto.getText()));
			usuario.setCargo(txtCargo.getText());
			usuario.setTempo_Emp(Integer.parseInt(txtTempo_Emp.getText()));
			usuario.setSalario(Double.parseDouble(txtSalario.getText()));
			usuario.setComissao(Double.parseDouble(txtComissao.getText()));	
			usuarioDAO.update(usuario);;    // Atualizar usuário
		} else
		{
			usuario.setIdEmpregado(Integer.parseInt(txtIdEmpregado.getText()));
			usuario.setNomeEmpregado(txtNomeEmpregado.getText());
			usuario.setIdDepto(Integer.parseInt(txtIdDepto.getText()));
			usuario.setCargo(txtCargo.getText());
			usuario.setTempo_Emp(Integer.parseInt(txtTempo_Emp.getText()));
			usuario.setSalario(Double.parseDouble(txtSalario.getText()));
			usuario.setComissao(Double.parseDouble(txtComissao.getText()));
			usuarioDAO.insert(usuario);   // Incluir usuário
		}
	}

	public void excluir(int id){	
		Empregado usuario = new Empregado();
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		int resp =JOptionPane.showConfirmDialog(null, "Confirma a Exclusão ?", "Exclusão", JOptionPane.YES_NO_OPTION);	
		if (resp == JOptionPane.YES_OPTION){
			if (this.pesquisar(txtIdEmpregado.getText(), "IdUsuario", true)) {
				usuarioDAO.delete(id);;  // Excluir Usuário
				this.limpar();
			} 
		}	
	}
}
