package testes;

import static org.junit.Assert.*;

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
		assertEquals("001", agencia.obterIdentificador());
		assertEquals("Centro", agencia.obterNome());
		assertEquals(banco, agencia.obterBanco());
	}
}