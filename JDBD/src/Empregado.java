
//@Author Diego LIns 07/01

public class Empregado {

	private int IdEmpregado;
	private String NomeEmpregado;
	private int IdDepto;
	private String Cargo;
	private int Tempo_Emp;
	private double Salario;
	private double Comissao;

	public Empregado(int idEmpregado, String nomeEmpregado, int idDepto, String cargo, int tempo_Emp, double salario,
			double comissao) {
		setIdEmpregado(idEmpregado);
		setNomeEmpregado(nomeEmpregado);
		setIdDepto(idDepto);
		setCargo(cargo);
		setTempo_Emp(tempo_Emp);
		setSalario(salario);
		setComissao(comissao);
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
