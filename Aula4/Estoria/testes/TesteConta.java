package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.ine.leb.projetos.estoria.Fixture;
import br.ufsc.ine.leb.projetos.estoria.FixtureSetup;
import br.ufsc.ine.leb.sistemaBancario.*;

@FixtureSetup(TesteAgencia.class)
public class TesteConta {

	@Fixture private Agencia agencia;
	private Conta conta;

	@Before
	public void configurar() {
		conta = agencia.criarConta("Maria");
	}

	@Test
	public void verificarCriacaoConta() throws Exception {
		assertEquals("0001-5", conta.obterIdentificador());
		assertEquals("Maria", conta.obterTitular());
		assertTrue(conta.calcularSaldo().zero());
		assertEquals("Centro", agencia.obterNome());
	}
}
