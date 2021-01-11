
public class Teste {

	public static void main(String[] args) {

		UsuarioDAO usu = new UsuarioDAO();
		//usu.insert(new Empregado (203,"Tais Lins",10,"TLM", 1 , 500, 150));
		usu.update(new Empregado (203,"Tais Lins",10,"TLM", 1 , 500, 150));

	}
}