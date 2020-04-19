package ex_1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaisTest {
	
	Pais original, copia, menor;
	static int id = 0;
	
	@Before
	public void setUp() {
		original = new Pais();
		original.setId(id);
		original.setNome("Chile");
		original.setPop(18);
		original.setArea(759950.0);
		copia = new Pais();
		copia.setId(id);
		copia.setNome("Chile");
		copia.setPop(18);
		copia.setArea(759950.00);
		System.out.println(original);
		System.out.println(copia);
		System.out.println(id);
	}
	
	@Test
	public void test00Carregar() {
		System.out.println("carregar");
		Pais fixture = new Pais();
		fixture.setId(1);
		fixture.setNome("Brasil");
		fixture.setPop(209);
		fixture.setArea(8511000.00);
		Pais novo = Pais.carregar(1);
		assertEquals("testa inclusao", novo, fixture);
	}
	
	@Test
	public void test01Criar() {
		System.out.println("criar");
		id = original.criar(original);
		System.out.println(id);
		copia.setId(id);
		assertEquals("testa criacao", original, copia);
	}
	
	@Test
	public void test02Atualizar() {
		System.out.println("atualizar");
		original.setPop(999);
		copia.setPop(999);		
		Pais.atualizar(id, original);
		original = Pais.carregar(original.getId());
		assertEquals("testa atualizacao", original, copia);
	}
	
	@Test
	public void test03Excluir() {
		System.out.println("excluir");
		copia.setId(-1);
		copia.setNome(null);
		copia.setPop(0);
		copia.setArea(0);
		Pais.excluir(id);
		original = Pais.carregar(id);
		assertEquals("testa exclusao", original, copia);
	}
	
	@Test
	public void test04Maior() {
		System.out.println("Maior Populacao");
		original.setId(2);
		original.setNome("EUA");
		original.setPop(327);
		original.setArea(9834000.00);
		copia = Pais.getMaiorP();
		assertEquals(original, copia);		
	}
	
	@Test
	public void test05Menor() {
		System.out.println("Menor area");
		original.setId(3);
		original.setNome("Argentina");
		original.setPop(44);
		original.setArea(2780000.0);
		copia = Pais.getMenorA();
		assertEquals(original, copia);
	}
	
	@Test
	public void test06Vetor() {
		Pais v[] = Pais.vetor();
		Pais pais[] = {new Pais(1, "Brasil", 209, 8511000.0), new Pais(2, "EUA", 307, 9834000.0), new Pais(3, "Argentina", 44, 2780000.0)};
		assertArrayEquals(pais, v);
	}
}
