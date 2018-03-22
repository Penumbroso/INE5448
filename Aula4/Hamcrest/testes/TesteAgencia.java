package testes;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;

import br.ufsc.ine.leb.projetos.estoria.Fixture;

import br.ufsc.ine.leb.projetos.estoria.*;
import br.ufsc.ine.leb.sistemaBancario.*;

@FixtureSetup(TesteBanco.class)
public class TesteAgencia {

	@Fixture private Banco banco;
	private Agencia agencia;

	@Before
	public void configurar() {
		agencia = banco.criarAgencia("Centro");
	}

	@Test
	public void criarAgenciaCentro() throws Exception {
		//assertEquals("001", agencia.obterIdentificador());
		assertThat(agencia.obterIdentificador(), is(equalTo("001")));
		//assertEquals("Centro", agencia.obterNome());
		assertThat(agencia.obterNome(), is(equalToIgnoringCase("centro")));
		//assertEquals(banco, agencia.obterBanco());
		assertThat(agencia.obterBanco(), equalTo(banco));
	}
}