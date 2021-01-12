
//@Author Diego LIns 07/01

public class Empregado {

	private int IdEmpregado;
	private String NomeEmpregado;
	private int IdDepto;
	private String Cargo;
	private int Tempo_Emp;
	private double Salario;
	private double Comissao;
	
	public Empregado(){
		
	}

	public Empregado(int IdEmpregado, String NomeEmpregado, int IdDepto, String Cargo, int Tempo_Emp, double Salario,
			double Comissao) {
		setIdEmpregado(IdEmpregado);
		setNomeEmpregado(NomeEmpregado);
		setIdDepto(IdDepto);
		setCargo(Cargo);
		setTempo_Emp(Tempo_Emp);
		setSalario(Salario);
		setComissao(Comissao);
	}

	public int getIdEmpregado() {
		return IdEmpregado;
	}

	public void setIdEmpregado(int idEmpregado) {
		IdEmpregado = idEmpregado;
	}

	public String getNomeEmpregado() {
		return NomeEmpregado;
	}

	public void setNomeEmpregado(String nomeEmpregado) {
		NomeEmpregado = nomeEmpregado;
	}

	public int getIdDepto() {
		return IdDepto;
	}

	public void setIdDepto(int idDepto) {
		IdDepto = idDepto;
	}

	public String getCargo() {
		return Cargo;
	}

	public void setCargo(String cargo) {
		Cargo = cargo;
	}

	public int getTempo_Emp() {
		return Tempo_Emp;
	}

	public void setTempo_Emp(int tempo_Emp) {
		Tempo_Emp = tempo_Emp;
	}

	public double getSalario() {
		return Salario;
	}

	public void setSalario(double salario) {
		Salario = salario;
	}

	public double getComissao() {
		return Comissao;
	}

	public void setComissao(double comissao) {
		Comissao = comissao;
	}

	@Override
	public String toString() {
		return "Empregado [IdEmpregado=" + IdEmpregado + ", NomeEmpregado=" + NomeEmpregado + ", IdDepto=" + IdDepto
				+ ", Cargo=" + Cargo + ", Tempo_Emp=" + Tempo_Emp + ", Salario=" + Salario + ", Comissao=" + Comissao
				+ "]";
	}

}
