package testes;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

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
		//assertEquals("0001-5", conta.obterIdentificador());
		assertThat(conta.obterIdentificador(), is(equalTo("0001-5")));
		//assertEquals("Maria", conta.obterTitular());
		assertThat(conta.obterTitular(), is(equalToIgnoringCase("maria")));
		//assertTrue(conta.calcularSaldo().zero());
		assertThat(conta.calcularSaldo().obterQuantia().obterQuantiaEmEscala(), allOf(is(not(greaterThan(0))), is(not(lessThan(0)))));
		//assertEquals("Centro", agencia.obterNome());
		assertThat(agencia.obterNome(), is(equalToIgnoringCase("centro")));
	}
}
