package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.ine.leb.sistemaBancario.Agencia;
import br.ufsc.ine.leb.sistemaBancario.Banco;
import br.ufsc.ine.leb.sistemaBancario.Conta;
import br.ufsc.ine.leb.sistemaBancario.Dinheiro;
import br.ufsc.ine.leb.sistemaBancario.EstadosDeOperacao;
import br.ufsc.ine.leb.sistemaBancario.Moeda;
import br.ufsc.ine.leb.sistemaBancario.Operacao;
import br.ufsc.ine.leb.sistemaBancario.SistemaBancario;
import br.ufsc.ine.leb.sistemaBancario.ValorMonetario;

public class TestesSistemaBancario {

	private SistemaBancario sistemaBancario;
	
	@Before
	public void criaSistemaBancario() {
		sistemaBancario = new SistemaBancario();
	}
	
	@Test
	public void criaBancoDoBrasil() {
		//Fixture setup (IMPLICIT)
		
		//Exercise SUT
		Banco bb = sistemaBancario.criarBanco("Banco do Brasil", Moeda.BRL);
		//Result verification
		assertEquals("Banco do Brasil", bb.obterNome());
		assertEquals(Moeda.BRL, bb.obterMoeda());
		//Fixture teardown
	}
	
	@Test
	public void criaAgenciaNoBancoDoBrasil() {
		//Fixture setup (IMPLICIT & INLINE)
		Banco bb = sistemaBancario.criarBanco("Banco do Brasil", Moeda.BRL);
		
		//Exercise SUT
		Agencia agenciaUFSC = bb.criarAgencia("Agencia UFSC");
		
		//Result verification
		assertEquals(bb, agenciaUFSC.obterBanco());
		//Fixture teardown
	}
	
	@Test
	public void criaContaNaAgenciaUFSCComTitularRicardo() {
		//Fixture setup (IMPLICIT & INLINE)
		Banco bb = sistemaBancario.criarBanco("Banco do Brasil", Moeda.BRL);
		Agencia agenciaUFSC = bb.criarAgencia("Agencia UFSC");
		
		//Exercise SUT
		Conta contaDoRicardo = agenciaUFSC.criarConta("Ricardo");
		
		//Result verification
		assertEquals("Ricardo", contaDoRicardo.obterTitular());
		assertEquals(agenciaUFSC, contaDoRicardo.obterAgencia());
		
		//Fixture teardown
	}
	
	@Test
	public void depositoEmContaComValorDeDezReais() throws Exception{
		//Fixture setup (IMPLICIT & DELEGATED)
		Conta contaDoRicardo= TestHelper.criaContaDoRicardo();
		Dinheiro dezReais = TestHelper.criaDezReais();	
		
		//Exercise SUT
		Operacao depositoDeDezReais = sistemaBancario.depositar(contaDoRicardo, dezReais);
		
		//Result verification
		assertEquals(EstadosDeOperacao.SUCESSO, depositoDeDezReais.obterEstado());
		assertEquals(new ValorMonetario(Moeda.BRL).somar(dezReais), contaDoRicardo.calcularSaldo());
		
		//Fixture teardown
	}
	
	@Test
	public void depositoEmContaComValorDeZeroReais() throws Exception{
		//Fixture setup (IMPLICIT & DELEGATED & INLINE)
		Conta contaDoRicardo= TestHelper.criaContaDoRicardo();
		Dinheiro ZeroReais = new Dinheiro(Moeda.BRL, 0, 0);	
		
		//Exercise SUT
		Operacao depositoDeZeroReais = sistemaBancario.depositar(contaDoRicardo, ZeroReais);
		
		//Result verification
		assertEquals(EstadosDeOperacao.SUCESSO, depositoDeZeroReais.obterEstado());
		assertEquals(new ValorMonetario(Moeda.BRL).somar(ZeroReais), contaDoRicardo.calcularSaldo());
		
		//Fixture teardown
	}
	
	@Test
	public void depositaEmContaDuasVezesComValorDeDezReais() throws Exception{
		//Fixture setup (IMPLICIT & DELEGATED)
		Conta contaDoRicardo= TestHelper.criaContaDoRicardo();
		Dinheiro dezReais = TestHelper.criaDezReais();
		ValorMonetario vinteReaisEmValorMonetario = TestHelper.criaVinteReais();
		sistemaBancario.depositar(contaDoRicardo, dezReais);
		
		//Exercise SUT
		Operacao depositoDeDezReais = sistemaBancario.depositar(contaDoRicardo, dezReais);
		
		//Result verification
		assertEquals(EstadosDeOperacao.SUCESSO, depositoDeDezReais.obterEstado());
		assertEquals(vinteReaisEmValorMonetario, contaDoRicardo.calcularSaldo());
		
		//Fixture teardown
	}
	
	@Test
	public void saqueEmContaComSaldoSuficiente() throws Exception{
		//Fixture setup (IMPLICIT & DELEGATED & INLINE)
		Conta contaDoRicardo= TestHelper.criaContaDoRicardo();
		Dinheiro dezReais = TestHelper.criaDezReais();
		sistemaBancario.depositar(contaDoRicardo, dezReais);
		
		//Exercise SUT
		Operacao saqueDeDezReais = sistemaBancario.sacar(contaDoRicardo, dezReais);
		
		//Result verification
		assertEquals(EstadosDeOperacao.SUCESSO,saqueDeDezReais.obterEstado());
		assertEquals(new ValorMonetario(Moeda.BRL), contaDoRicardo.calcularSaldo());
		
		//Fixture teardown
	}
	
	@Test
	public void saqueEmContaComSaldoInsuficiente() throws Exception{
		//Fixture setup (IMPLICIT & DELEGATED)
		Conta contaDoRicardo= TestHelper.criaContaDoRicardo();
		Dinheiro dezReais = TestHelper.criaDezReais();
		
		//Exercise SUT
		Operacao saqueDeDezReais = sistemaBancario.sacar(contaDoRicardo, dezReais);
		
		//Result verification
		assertEquals(EstadosDeOperacao.SALDO_INSUFICIENTE, saqueDeDezReais.obterEstado());
		assertEquals(new ValorMonetario(Moeda.BRL), contaDoRicardo.calcularSaldo());
		
		//Fixture teardown
	}
	
	@Test
	public void saqueEmContaComMoedaDiferente() throws Exception{
		//Fixture setup (IMPLICIT & DELEGATED)
		Conta contaDoRicardo= TestHelper.criaContaDoRicardo();
		Dinheiro dezDolares = TestHelper.criaDezDolares();
		
		//Exercise SUT
		Operacao saqueDeDezReais = sistemaBancario.sacar(contaDoRicardo, dezDolares);
		
		//Result verification
		assertEquals(EstadosDeOperacao.MOEDA_INVALIDA, saqueDeDezReais.obterEstado());
		assertEquals(new ValorMonetario(Moeda.BRL), contaDoRicardo.calcularSaldo());
	}

	@Test
	public void tranferirDeDezReaisEntreContas() throws Exception{
		//Fixture setup (IMPLICIT & DELEGATED & INLINE)
		Conta contaDoRicardo= TestHelper.criaContaDoRicardo();
		Conta contaDaMaria = TestHelper.criaContaDoMaria();
		Dinheiro dezReais = TestHelper.criaDezReais();
		sistemaBancario.depositar(contaDoRicardo, dezReais);
		sistemaBancario.depositar(contaDaMaria, dezReais);
		ValorMonetario vinteReaisEmValorMonetario = new ValorMonetario(Moeda.BRL).somar(new Dinheiro(Moeda.BRL, 20, 0));
		
		//Exercise SUT
		Operacao transferirDezReaisDeRicardoParaMaria = sistemaBancario.transferir(contaDoRicardo, contaDaMaria, dezReais);
		
		//Result verification
		assertEquals(EstadosDeOperacao.SUCESSO, transferirDezReaisDeRicardoParaMaria.obterEstado());
		assertEquals(new ValorMonetario(Moeda.BRL), contaDoRicardo.calcularSaldo());
		assertEquals(vinteReaisEmValorMonetario, contaDaMaria.calcularSaldo());
	}
}
