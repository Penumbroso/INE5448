package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import core.EstadoOcorrencia;
import core.Funcionario;
import core.Ocorrencia;
import core.PrioridadeOcorrencia;
import core.TipoOcorrencia;

public class OcorrenciaTest {
	Ocorrencia ocorrenciaTarefaBaixaAberta;
	Ocorrencia ocorrenciaTarefaBaixaFechada;
	
	@Before
	public void setUp() throws Exception {
		ocorrenciaTarefaBaixaAberta = new Ocorrencia(new Funcionario("Joao"));
		ocorrenciaTarefaBaixaAberta.setTipo(TipoOcorrencia.TAREFA);
		ocorrenciaTarefaBaixaAberta.setPrioridade(PrioridadeOcorrencia.BAIXA);
		ocorrenciaTarefaBaixaAberta.setEstado(EstadoOcorrencia.ABERTA);
		
		ocorrenciaTarefaBaixaFechada = new Ocorrencia(new Funcionario("Joao"));
		ocorrenciaTarefaBaixaFechada.setTipo(TipoOcorrencia.TAREFA);
		ocorrenciaTarefaBaixaFechada.setPrioridade(PrioridadeOcorrencia.BAIXA);
		ocorrenciaTarefaBaixaFechada.setEstado(EstadoOcorrencia.FECHADA);
	}

	@Test
	public void testOcorrenciaNotNull() {
		assertNotNull(ocorrenciaTarefaBaixaAberta);
	}
	
	@Test
	public void testResponsavelPorOcorrenciaNotNull(){
		assertNotNull(ocorrenciaTarefaBaixaAberta.getResponsavel());
	}
	
	@Test
	public void testOcorrenciaNaoConcluida(){
		assertFalse(ocorrenciaTarefaBaixaAberta.isConcluida());
	}
	
	@Test
	public void testOcorrenciaConcluida(){
		assertTrue(ocorrenciaTarefaBaixaFechada.isConcluida());
	}
	
	@Test
	public void testOcorrenciaPossuiChave(){
		assertNotNull(ocorrenciaTarefaBaixaAberta.getChaveUnica());
	}
	
	@Test
	public void testTipoOcorrenciaTarefaTarefa(){
		assertEquals(TipoOcorrencia.TAREFA, ocorrenciaTarefaBaixaAberta.getTipoOcorrencia());
	}
	
	@Test
	public void testModificarPrioridadeDeBaixaParaAlta(){
		ocorrenciaTarefaBaixaAberta.setPrioridade(PrioridadeOcorrencia.ALTA);
		assertEquals(PrioridadeOcorrencia.ALTA, ocorrenciaTarefaBaixaAberta.getPrioridade());
	}
	
	@Test
	public void testModificarPrioridadeDeBaixaParaMedia(){
		ocorrenciaTarefaBaixaAberta.setPrioridade(PrioridadeOcorrencia.MEDIA);
		assertEquals(PrioridadeOcorrencia.MEDIA, ocorrenciaTarefaBaixaAberta.getPrioridade());
	}
	
	@Test
	public void testModificarPrioridadeDeOcorrenciaFechada(){
		assertFalse(ocorrenciaTarefaBaixaFechada.setPrioridade(PrioridadeOcorrencia.ALTA));
	}
	
	@Test
	public void testFalhaEmModificarPrioridadeDeOcorrenciaFechada(){
		ocorrenciaTarefaBaixaFechada.setPrioridade(PrioridadeOcorrencia.ALTA);
		assertEquals(PrioridadeOcorrencia.BAIXA, ocorrenciaTarefaBaixaFechada.getPrioridade());
	}

	@Test
	public void testModificarResponsavelDeOcorrenciaFechada(){
		assertFalse(ocorrenciaTarefaBaixaFechada.setResponsavel(new Funcionario("Joao")));
	}
	
	@Test
	public void testModificarResponsavelDeOcorrenciaAberta(){
		assertTrue(ocorrenciaTarefaBaixaAberta.setResponsavel(new Funcionario("Joao")));
	}
	
	@Test
	public void testChavesSaoUnicas(){
		Ocorrencia ocorrencia = new Ocorrencia(new Funcionario("Maria"));
		assertNotEquals(this.ocorrenciaTarefaBaixaAberta.getChaveUnica(), ocorrencia.getChaveUnica());
	}
}
