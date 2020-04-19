package ex_2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaisTest2 {
	
	Pais original, copia, menor;
	Service cs;
	static int id = 0;

	@Before
	public void setUp() {
		original = new Pais();
		original.setId(id);
		original.setnome("Chile");
		original.setPop(18);
		original.setArea(759950.0);
		copia = new Pais();
		copia.setId(id);
		copia.setnome("Chile");
		copia.setPop(18);
		copia.setArea(759950.00);
		cs = new Service();
		System.out.println(original);
		System.out.println(copia);
		System.out.println(id);
	}
	
	@Test
	public void test00Carregar() {
		System.out.println("Carregar");
		Pais fixture = new Pais(1,"Brasil", 209, 8511000.0);
		Pais novo = new Pais();
		novo.setId(1);
		novo.carregar();
		assertEquals(fixture, novo);
	}
	
	@Test
	public void test01Criar() {
		System.out.println("Criar teste");
		original.criar();
		assertEquals(original, copia);
	}
	
	@Test
	public void test02Atualizar() {
		System.out.println("Atualizar");
		original.setPop((999));
		copia.setPop(999);
		original.atualizar();
		assertEquals(original, copia);
	}
	
	@Test
	public void test03Excluir() {
		System.out.println("excluir");
		copia.setId(-1);
		copia.setnome(null);
		copia.setPop(0);
		copia.setArea(0);
		original.excluir();
		assertEquals(original, copia);
	}
	
	@Test
	public void test04Maior() {
		System.out.println("Maior Populacao");
		original.setId(2);
		original.setnome("EUA");
		original.setPop(327);
		original.setArea(9834000.00);
		copia.getMaiorP();
		assertEquals(original, copia);		
	}
	
	@Test
	public void test05Menor() {
		System.out.println("Menor area");
		original.setId(3);
		original.setnome("Argentina");
		original.setPop(44);
		original.setArea(2780000.0);
		copia.getMenorA();
		assertEquals(original, copia);
	}
	
	@Test
	public void test06Vetor() {
		Pais v[] = Service.vetor();
		Pais pais[] = {new Pais(1, "Brasil", 209, 8511000.0), new Pais(2, "EUA", 327, 9834000.0), new Pais(3, "Argentina", 44, 2780000.0)};
		assertArrayEquals(pais, v);
	}

}
