package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import core.EstadoOcorrencia;
import core.Funcionario;
import core.Ocorrencia;
import core.Projeto;

public class ProjetoTest {

	Projeto projeto;
	Ocorrencia ocorrencia;
	Funcionario funcionario;
	
	@Before
	public void setUp() throws Exception {
		projeto = new Projeto();
		funcionario = new Funcionario("Joao");
		ocorrencia = new Ocorrencia(funcionario);
		projeto.adicionarOcorrencia(ocorrencia);
		
	}

	@Test
	public void testProjetoNotNull() {
		assertNotNull(projeto);
	}

	@Test
	public void testProjetoTemOcorrencias(){
		assertNotNull(projeto.getOcorrencias());
	}
	
	@Test
	public void testAdicionarOcorrencia(){
		Ocorrencia ocorrencia = new Ocorrencia(this.funcionario);
		assertTrue(projeto.adicionarOcorrencia(ocorrencia));
	}
	
	@Test
	public void testTerminarOcorrencia(){
		ArrayList<Ocorrencia> ocorrencias = projeto.getOcorrencias();
		assertTrue(projeto.terminarOcorrencia(ocorrencias.get(0)));
	}
	
	@Test
	public void testFuncionarioNaoPossuiOcorrenciaTerminada(){
		Ocorrencia ocorrencia = projeto.getOcorrencias().get(0);
		Funcionario funcionario = ocorrencia.getResponsavel();
		projeto.terminarOcorrencia(ocorrencia);
		
		assertFalse(funcionario.estaTrabalhandoNaOcorrencia(ocorrencia));
	}
	
	@Test
	public void testTerminarOcorrenciaNaoExistente(){
		assertFalse(projeto.terminarOcorrencia(new Ocorrencia(funcionario)));
	}
	
	@Test
	public void testTerminarOcorrenciaFechada(){
		ArrayList<Ocorrencia> ocorrencias = projeto.getOcorrencias();
		ocorrencias.get(0).setEstado(EstadoOcorrencia.FECHADA);
		assertFalse(projeto.terminarOcorrencia(ocorrencias.get(0)));
	}
	
	@Test
	public void testAdicionarUndecimaOcorrencia(){
		
		Funcionario funcionarioComDezOcorrencias = new Funcionario("Joao");
		for(int i = 0; i < 10; i++)
			funcionarioComDezOcorrencias.adicionarOcorrencia(new Ocorrencia(funcionarioComDezOcorrencias));
		
		assertFalse(projeto.adicionarOcorrencia(new Ocorrencia(funcionarioComDezOcorrencias)));
	}
	
	@Test
	public void testAdicionarDecimaOcorrencia(){
		
		Funcionario funcionarioComNoveOcorrencias = new Funcionario("Joao");
		for(int i = 0; i < 9; i++)
			funcionarioComNoveOcorrencias.adicionarOcorrencia(new Ocorrencia(funcionarioComNoveOcorrencias));
		
		assertTrue(projeto.adicionarOcorrencia(new Ocorrencia(funcionarioComNoveOcorrencias)));
	}
}
