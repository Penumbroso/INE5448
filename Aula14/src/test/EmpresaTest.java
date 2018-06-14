package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import core.Empresa;
import core.Funcionario;
import core.Ocorrencia;
import core.Projeto;

public class EmpresaTest {

	private Empresa empresa;

	@Before
	public void setUp() throws Exception {
		empresa = new Empresa();
	}

	@Test
	public void testEmpresaNotNull() {
		assertNotNull(empresa);
	}

	@Test
	public void testEmpresaTemFuncionarios(){
		ArrayList<Funcionario> funcionarios = empresa.getFuncionarios();
		assertNotNull(funcionarios);
	}
	
	@Test
	public void testEmpresaTemUmProjeto(){
		Projeto projeto = new Projeto();
		empresa.adicionarProjeto(projeto);
		assertEquals(1, empresa.getNumeroDeProjetos());
	}
	
	@Test
	public void testAdicionarUmProjeto(){
		Projeto projeto = new Projeto();
		assertTrue(empresa.adicionarProjeto(projeto));
	}
	
	@Test
	public void testContemProjetoCriado(){
		Projeto projeto = new Projeto();
		empresa.adicionarProjeto(projeto);
		assertTrue(empresa.contemProjeto(projeto));
	}
	
	@Test
	public void testAdionarOMesmoProjetoDuasVezes(){
		Projeto projeto = new Projeto();
		empresa.adicionarProjeto(projeto);
		assertFalse(empresa.adicionarProjeto(projeto));
	}
	
	@Test
	public void testEmpresaTemZeroProjetos(){
		assertEquals(0, empresa.getNumeroDeProjetos());
	}
	
	@Test
	public void testEmpresaTemZeroFuncionarios(){
		assertEquals(0, empresa.getNumeroDeFuncionarios());
	}
	
	@Test
	public void testEmpresaTemUmFuncionario(){
		empresa.adicionarFuncionario(new Funcionario("Joao"));
		assertEquals(1, empresa.getNumeroDeFuncionarios());
	}
	
	@Test
	public void testCriarProjeto(){
		assertTrue(empresa.criarProjeto());
	}
	
	@Test
	public void testAdicionarFuncionario(){
		assertTrue(empresa.adicionarFuncionario(new Funcionario("Joao")));
	}
	
	@Test
	public void testAdicionarMesmoFuncionarioDuasVezes(){
		Funcionario funcionario = new Funcionario("Joao");
		empresa.adicionarFuncionario(funcionario);
		assertFalse(empresa.adicionarFuncionario(funcionario));
	}
	
	@Test
	public void testAdicionarOcorrenciaComFuncionarioDaEmpresa(){
		Funcionario joao = new Funcionario("Joao");
		this.empresa.criarProjeto();
		this.empresa.adicionarFuncionario(joao);
		Projeto projeto = this.empresa.getProjetos().get(0);
		Ocorrencia ocorrencia = new Ocorrencia(joao);
		
		assertTrue(this.empresa.adicionarOcorrencia(projeto, ocorrencia));
		
	}
	
	@Test
	public void testAdicionarOcorrenciaEmProjetoNaoExistente(){
		Funcionario joao = new Funcionario("Joao");
		this.empresa.criarProjeto();
		this.empresa.adicionarFuncionario(joao);
		Projeto projeto = new Projeto();
		Ocorrencia ocorrencia = new Ocorrencia(joao);
		
		assertFalse(this.empresa.adicionarOcorrencia(projeto, ocorrencia));
	}
	
	@Test
	public void testAdicionarOcorrenciaComFuncionarioInexistente(){
		this.empresa.criarProjeto();
		Funcionario joao = new Funcionario("Joao");
		this.empresa.criarProjeto();
		Projeto projeto = this.empresa.getProjetos().get(0);
		Ocorrencia ocorrencia = new Ocorrencia(joao);
		
		assertFalse(this.empresa.adicionarOcorrencia(projeto, ocorrencia));
	}

	
}
