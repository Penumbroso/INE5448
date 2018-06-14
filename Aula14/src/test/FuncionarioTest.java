package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import core.Funcionario;
import core.Ocorrencia;

public class FuncionarioTest {

	Funcionario funcionario;
	
	@Before
	public void setUp() throws Exception {
		funcionario = new Funcionario("Joao");
	}

	@Test
	public void testFuncionarioNotNull() {
		assertNotNull(funcionario);
	}
	
	@Test
	public void testAdicionarUmaOcorrencia(){
		assertTrue(this.funcionario.adicionarOcorrencia(new Ocorrencia(funcionario)));
	}
	
	@Test
	public void testAdicionarMaisDeDezOcorrencias(){
		for(int i = 0; i < 10; i++){
			this.funcionario.adicionarOcorrencia(new Ocorrencia(funcionario));
		}
		
		assertFalse(this.funcionario.adicionarOcorrencia(new Ocorrencia(funcionario)));
	}
	
	@Test
	public void testAdicionarDezOcorencias(){
		for(int i = 0; i < 9; i++){
			this.funcionario.adicionarOcorrencia(new Ocorrencia(funcionario));
		}
		
		assertTrue(this.funcionario.adicionarOcorrencia(new Ocorrencia(funcionario)));
	}

}
