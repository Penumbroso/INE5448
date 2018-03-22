package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.ine.leb.sistemaBancario.*;

public class TesteBanco {

	private SistemaBancario sistemaBancario;
	private Banco banco;

	@Before
	public void configurar() {
		sistemaBancario = new SistemaBancario();
		banco = sistemaBancario.criarBanco("Banco do Brasil", Moeda.BRL);
	}

	@Test
	public void criarBancoDoBrasil() throws Exception {
		assertEquals("Banco do Brasil", banco.obterNome());
		assertEquals(Moeda.BRL, banco.obterMoeda());
	}
}