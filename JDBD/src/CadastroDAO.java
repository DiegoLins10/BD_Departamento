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
			if (!(txtCodigo.getText().isEmpty())) {
				pesquisar(txtCodigo.getText(),"IdUsuario",true);
			} else if (!(txtNome.getText().isEmpty())){
				pesquisar(txtNome.getText(),"nome",true);				
			} else if (!(txtEmail.getText().isEmpty())){
				pesquisar(txtEmail.getText(),"email",true);								
			} else {
				JOptionPane.showMessageDialog(null, "Por favor, \nDigite o Código, Nome ou o Email !!!");
			}
		} else if (e.getSource() == btLimpar) {
			this.limpar();	
		} else if (e.getSource() == btExcluir) {
			int id = Integer.valueOf(txtCodigo.getText());
			this.excluir(id);
		} else if (e.getSource() == btSalvar) {
			this.salvar();
		}
	}

	public boolean pesquisar(String texto, String campo, boolean atualizaTela){
		boolean flagEncontrado=false;
		Empregado usuario = new Empregado();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (campo.equals("IdUsuario")) {
			usuario = usuarioDAO.select(Integer.parseInt(texto));    // Consultar usuário pelo Id
		} else  {
			usuario = usuarioDAO.select(campo, texto);    // Consultar usuário pelo Nome/Email		
		} 
	
		System.out.println(usuario);
		if (!(usuario == null)){
			if (atualizaTela) {
				txtCodigo.setText(String.valueOf(usuario.getIdUsuario()));
				txtNome.setText(usuario.getNome());
				txtEmail.setText(usuario.getEmail());
				txtTelefone.setText(usuario.getTelefone());
				txtEndereco.setText(usuario.getEndereco());
			}
			flagEncontrado = true;
		} else if (atualizaTela) {
			JOptionPane.showMessageDialog(null, "Usuário não encontrado !!!");
		}			
		return flagEncontrado;
	}
	
	public void limpar(){
		txtCodigo.setText(null);
		txtNome.setText(null);
		txtEmail.setText(null);
		txtTelefone.setText(null);
		txtEndereco.setText(null);
	}
	
	public void salvar(){
		Empregado usuario = new Empregado();
		UsuarioDAO usuarioDAO = new UsuarioDAO();		
		
		if (this.pesquisar(txtCodigo.getText(), "IdUsuario", false)) {
			usuario.setIdUsuario(Integer.parseInt(txtCodigo.getText()));
			usuario.setNome(txtNome.getText());
			usuario.setEmail(txtEmail.getText());
			usuario.setTelefone(txtTelefone.getText());
			usuario.setEndereco(txtEndereco.getText());			
			usuarioDAO.update(usuario);;    // Atualizar usuário
		} else
		{
			usuario.setIdUsuario(Integer.parseInt(txtCodigo.getText()));
			usuario.setNome(txtNome.getText());
			usuario.setEmail(txtEmail.getText());
			usuario.setTelefone(txtTelefone.getText());
			usuario.setEndereco(txtEndereco.getText());
			usuarioDAO.insert(usuario);   // Incluir usuário
		}
	}

	public void excluir(int id){	
		Empregado usuario = new Empregado();
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		int resp =JOptionPane.showConfirmDialog(null, "Confirma a Exclusão ?", "Exclusão", JOptionPane.YES_NO_OPTION);	
		if (resp == JOptionPane.YES_OPTION){
			if (this.pesquisar(txtCodigo.getText(), "IdUsuario", true)) {
				usuarioDAO.delete(id);;  // Excluir Usuário
				this.limpar();
			} 
		}	
	}
}
