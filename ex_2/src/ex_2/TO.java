package ex_2;

public class TO {
	
	private int id;
	private String nome;
	private long populacao;
	private double area;
	
	public TO(int id, String nome, long populacao, double area) {
		this.id = id;
		this.nome = nome;
		this.populacao = populacao;
		this.area = area;
	}
	
	public TO() {
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public long getPop() {
		return populacao;
	}
	
	public double getArea() {
		return area;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setPopulacao(long populacao) {
		this.populacao = populacao;
	}
	
	public void setArea(double area) {
		this.area = area;
	}

}
