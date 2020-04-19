package ex_2;

public class Pais {
	
	private int id;
	private String nome;
	private long populacao;
	private double area;
	
	public Pais(){
		this.nome = nome;
		this.populacao = populacao;
		this.area = area;
		
	}
	public Pais(int id, String nome, long populacao, double area) {
		this.id = id;
		this.nome = nome;
		this.populacao = populacao;
		this.area = area;
	}
	
	public int getId() {
		return id;
		}
	
	public void setId(int id) {
		this.id = id;
		}
	
	public String getNome() {
		return nome;
		}
	
	public void setnome(String nome) {
		this.nome = nome;
		}
	
	public long getPop() {
		return populacao;
		}
	
	public void setPop(long populacao) {
		this.populacao = populacao;
		}
	
	public double getArea() {
		return area;
		}
	
	public void setArea(double area) {
		this.area = area;
		}
	
	
	@Override
	public String toString() {
	return"Pais [id=" + id + ", nome=" + nome + ", populacao=" + populacao
	+ ", area=" + area + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pais other = (Pais) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (populacao != other.populacao)
			return false;
		if (id != other.id)
			return false;
		if (area != other.area) 
			return false;
		return true;
	}
	
	public void criar() {
		TO to = new TO();
		Service.criar(to);
	}
	
	public void atualizar() {
		TO to = new TO();
		Service.atualizar(id, to);
	}
	
	public void excluir() {
		TO to = new TO();
		Service.excluir(id, to);
	}
	
	public void carregar() {
		TO to = Service.carregar(id);
		id = to.getId();
		nome = to.getNome();
		populacao = to.getPop();
		area = to.getArea();
	}
	
	public void getMaiorP() {
		TO to = Service.getMaiorP();
		id = to.getId();
		nome = to.getNome();
		populacao = to.getPop();
		area = to.getArea();
	}
	
	public void getMenorA() {
		TO to = Service.getMenorA();
		id = to.getId();
		nome = to.getNome();
		populacao = to.getPop();
		area = to.getArea();
	}
	public void vetor() {
		Service.vetor();
	}
}
