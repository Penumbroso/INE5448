package tests;
import static org.junit.Assert.*;
import br.ufsc.ine.leb.sistemaBancario.*;

import org.junit.Test;


public class TestesDinheiroEValorMonetario {
	
	@Test
	public void criaDezReaisECinquendaCentavos() {
		//Fixture setup
		
		//Exercise SUT
		Dinheiro dezReaisECinquentaCentavos = new Dinheiro(Moeda.BRL, 10, 50);
		
		//Result verification
		assertEquals(Moeda.BRL, dezReaisECinquentaCentavos.obterMoeda());
		assertEquals(new Integer(1050), dezReaisECinquentaCentavos.obterQuantiaEmEscala());
		
		//Fixture teardown
	}
	
	@Test
	public void criaDezReaisNegativos() {
		//Fixture setup
		
		//Exercise SUT
		Dinheiro dezReais = new Dinheiro(Moeda.BRL, -10, 0);
		
		//Result verification
		assertEquals(new Integer(1000), dezReais.obterQuantiaEmEscala());
		
		//Fixture teardown
	}
	
	@Test
	public void criaDezReaisPositivoECinquentaCentavosNegativos() {
		//Fixture setup
		
		//Exercise SUT
		Dinheiro dezReaisECinquentaCentavos = new Dinheiro(Moeda.BRL, 10, -50);
		
		//Result verification
		assertEquals(Moeda.BRL, dezReaisECinquentaCentavos.obterMoeda());
		assertEquals(new Integer(1050), dezReaisECinquentaCentavos.obterQuantiaEmEscala());
		
		//Fixture teardown
	}
	
	@Test
	public void dezReaisEIgualADezDolares() {
		//Fixture setup (DELEGATED)
		Dinheiro dezReais = TestHelper.criaDezReais();
		Dinheiro dezDolares = TestHelper.criaDezDolares();
		
		//Exercise SUT
		boolean dezReaisIgualDezDolares = dezReais.equals(dezDolares);
		
		//Result verification
		assertFalse(dezReaisIgualDezDolares);
		
		//Fixture teardown
	}
	
	// Valor Monetario
	
	@Test
	public void criaValorMonetarioESomarDezReais() {
		//Fixture setup (INLINE & DELEGATED)
		Dinheiro dezReais = TestHelper.criaDezReais();
		ValorMonetario valorMonetario = new ValorMonetario(Moeda.BRL);
		
		//Exercise SUT
		ValorMonetario dezReaisVM = valorMonetario.somar(dezReais);
		
		//Result verification
		assertEquals(dezReais, dezReaisVM.obterQuantia());
		
		//Fixture teardown
	}
	
	@Test
	public void criaValorMonetarioESomarVinteReais() {
		//Fixture setup (INLINE & DELEGATED)
		Dinheiro dezReais = TestHelper.criaDezReais();
		Dinheiro vinteReais = new Dinheiro(Moeda.BRL, 20, 0);
		ValorMonetario valorMonetario = new ValorMonetario(Moeda.BRL);
		
		//Exercise SUT
		ValorMonetario dezReaisVM = valorMonetario.somar(dezReais);
		ValorMonetario vinteReaisVM  = dezReaisVM.somar(dezReais);
		
		//Result verification
		assertEquals(vinteReais, vinteReaisVM.obterQuantia());

		//Fixture teardown
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void criaValorMonetarioEmRealESomaDezDolares() {
		//Fixture setup (DELEGATED & INLINE)
		Dinheiro dezDolares = TestHelper.criaDezDolares();
		ValorMonetario valorMonetario = new ValorMonetario(Moeda.BRL);
		
		//Exercise SUT
		valorMonetario.somar(dezDolares);
		
		//Result verification

		//Fixture teardown
	}
	
	@Test
	public void criaValorMonetarioESubtraiDezReais() {
		//Fixture setup (DELEGATED & INLINE)
		Dinheiro dezReais = TestHelper.criaDezReais();
		ValorMonetario valorMonetario = new ValorMonetario(Moeda.BRL);
		
		//Exercise SUT
		ValorMonetario dezReaisNegativosValorMonetario = valorMonetario.subtrair(dezReais);
		
		//Result verification
		assertEquals(dezReais, dezReaisNegativosValorMonetario.obterQuantia());
		
		//Fixture teardown
	}
	
	@Test
	public void testaValorMonetarioPositivo() {
		//Fixture setup (DELEGATED & INLINE)
		Dinheiro dezReais = TestHelper.criaDezReais();
		ValorMonetario valorMonetario = new ValorMonetario(Moeda.BRL);
		
		//Exercise SUT
		ValorMonetario dezReaisPositivosValorMonetario = valorMonetario.somar(dezReais);
		
		//Result verification
		assertFalse(dezReaisPositivosValorMonetario.negativo());
		
		//Fixture teardown
	}
	
	@Test
	public void testaValorMonetarioNegativo() {
		//Fixture setup (DELEGATED & INLINE)
		Dinheiro dezReais = TestHelper.criaDezReais();
		ValorMonetario valorMonetario = new ValorMonetario(Moeda.BRL);
		
		//Exercise SUT
		ValorMonetario dezReaisNegativosValorMonetario = valorMonetario.subtrair(dezReais);
		
		//Result verification
		assertTrue(dezReaisNegativosValorMonetario.negativo());
		
		//Fixture teardown
	}
	
	

}
