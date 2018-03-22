package testes;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import br.ufsc.ine.leb.projetos.estoria.Fixture;
import br.ufsc.ine.leb.projetos.estoria.FixtureSetup;
import br.ufsc.ine.leb.sistemaBancario.*;

@FixtureSetup({TesteConta.class, TesteBanco.class})
public class TesteOperacoes {

	@Fixture private Conta conta;
	@Fixture private SistemaBancario sistemaBancario;

	@Test
	public void depositar10Reais() throws Exception {
		Dinheiro _10Reais = new Dinheiro(Moeda.BRL, 10, 0);
		Operacao deposito10Reais = sistemaBancario.depositar(conta, _10Reais);
		
		//assertEquals(EstadosDeOperacao.SUCESSO, deposito10Reais.obterEstado());
		assertThat(deposito10Reais.obterEstado(), is(equalTo(EstadosDeOperacao.SUCESSO)));
		//assertEquals(_10Reais, conta.calcularSaldo().obterQuantia());
		assertThat(conta.calcularSaldo().obterQuantia(), is(equalTo(_10Reais)));
	}

	@Test
	public void sacarComSaldoSuficiente() throws Exception {
		Dinheiro _10Reais = new Dinheiro(Moeda.BRL, 10, 0);
		Dinheiro _6Reais = new Dinheiro(Moeda.BRL, 6, 0);
		Dinheiro _4Reais = new Dinheiro(Moeda.BRL, 4, 0);
		sistemaBancario.depositar(conta, _10Reais);
		
		Operacao saque6Reais = sistemaBancario.sacar(conta, _6Reais);
		
		//assertEquals(EstadosDeOperacao.SUCESSO, saque6Reais.obterEstado());
		assertThat(saque6Reais.obterEstado(), is(equalTo(EstadosDeOperacao.SUCESSO)));
		//assertEquals(_4Reais, conta.calcularSaldo().obterQuantia());
		assertThat(conta.calcularSaldo().obterQuantia(), is(equalTo(_4Reais)));
	}

	@Test
	public void sacarComSaldoInsuficiente() throws Exception {
		Dinheiro _4Reais = new Dinheiro(Moeda.BRL, 4, 0);
		Dinheiro _6Reais = new Dinheiro(Moeda.BRL, 6, 0);
		sistemaBancario.depositar(conta, _4Reais);
		
		Operacao saque6Reais = sistemaBancario.sacar(conta, _6Reais);
		
		//assertEquals(EstadosDeOperacao.SALDO_INSUFICIENTE, saque6Reais.obterEstado());
		assertThat(saque6Reais.obterEstado(), is(equalTo(EstadosDeOperacao.SALDO_INSUFICIENTE)));
		//assertEquals(_4Reais, conta.calcularSaldo().obterQuantia());
		assertThat(conta.calcularSaldo().obterQuantia(), is(equalTo(_4Reais)));
	}
}