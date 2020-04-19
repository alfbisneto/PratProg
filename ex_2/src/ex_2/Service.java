package ex_2;

public class Service {
	static DAO dao = new DAO();
	
	public static void criar(TO to) {
		dao.criar(to);
	}
	
	public static TO carregar(int id) {
		return dao.carregar(id);
	}
	
	public static void atualizar(int id, TO to) {
		dao.atualizar(id, to);
	}
	
	public static void excluir(int id,TO to) {
		dao.excluir(id, to);
	}
	
	public static TO getMaiorP() {
		return dao.getMaiorP();
	}
	
	public static TO getMenorA() {
		return dao.getMenorA();
	}
	
	public static Pais[] vetor() {
		return dao.vetor();

}	
	
}
