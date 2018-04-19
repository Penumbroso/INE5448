package modelo;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import interfaces.IMercadoLeilao;
import modelo.MercadoLeilao;

public class TesteProduto {
	
	private IMercadoLeilao mercado;
	private Date dataLimiteProduto;
	private String cpfLeiloadorProduto;
	private String nomeProduto;
	private String descricaoProduto;
	private Double valorMinimoProduto;
	private String nomeUsuario;
	private String enderecoUsuario;
	private String emailUsuario;
	
	@Before
	public void setup(){
		mercado = new MercadoLeilao();
		nomeProduto = "guitarra";
		descricaoProduto = "fender";
		valorMinimoProduto = 1000.0;
		dataLimiteProduto = new Date(new Date().getTime() + 3000);
	}
	
	@Test
	public void TesteMercadoComUmProdutoDoJoao() throws Exception {
		nomeUsuario = "Joao";
		enderecoUsuario = "Centro";
		emailUsuario = "joao@com";
		cpfLeiloadorProduto = "111.111.111";
		nomeProduto = "guitarra";
		descricaoProduto = "fender";
		valorMinimoProduto = 1000.0;
		dataLimiteProduto = new Date(new Date().getTime() + 3000);
		mercado.cadastrarUsuario(nomeUsuario, enderecoUsuario, emailUsuario, cpfLeiloadorProduto);
		mercado.cadastrarProduto(nomeProduto, descricaoProduto, valorMinimoProduto, cpfLeiloadorProduto, dataLimiteProduto);
		assertEquals("Joao", mercado.getUsuario(cpfLeiloadorProduto).getNome());
		assertEquals("guitarra", mercado.getProdutosEmLeilao().get(0).getNome());
	}
	
	@Test
	public void testeLancesdoUsuario() throws Exception{
		nomeUsuario = "Joao";
		enderecoUsuario = "Centro";
		emailUsuario = "joao@com";
		cpfLeiloadorProduto = "111.111.111";
		nomeProduto = "guitarra";
		descricaoProduto = "fender";
		valorMinimoProduto = 1000.0;
		dataLimiteProduto = new Date(new Date().getTime() + 3000);
		
		mercado.cadastrarUsuario("Nome", "Centro", "nome@nome", "999.999.999");
		mercado.cadastrarUsuario(nomeUsuario, enderecoUsuario, emailUsuario, cpfLeiloadorProduto);
		mercado.cadastrarProduto(nomeProduto, descricaoProduto, valorMinimoProduto, cpfLeiloadorProduto, dataLimiteProduto);
		mercado.daLance(nomeProduto, "999.999.999", 1001.0);
		
		Produto produto = (Produto) mercado.getProdutosEmLeilao().get(0);
		
		List<Lance> lances = produto.lancesEfetuadosPorUmUsuario("999.999.999");
		
		assertEquals(1, lances.size());
		
	}
	
	@Test(expected=Exception.class)
	public void testeLanceMenor() throws Exception{
		nomeUsuario = "Joao";
		enderecoUsuario = "Centro";
		emailUsuario = "joao@com";
		cpfLeiloadorProduto = "111.111.111";
		nomeProduto = "guitarra";
		descricaoProduto = "fender";
		valorMinimoProduto = 1000.0;
		dataLimiteProduto = new Date(new Date().getTime() + 3000);
		
		mercado.cadastrarUsuario("Nome", "Centro", "nome@nome", "999.999.999");
		mercado.cadastrarUsuario(nomeUsuario, enderecoUsuario, emailUsuario, cpfLeiloadorProduto);
		mercado.cadastrarProduto(nomeProduto, descricaoProduto, valorMinimoProduto, cpfLeiloadorProduto, dataLimiteProduto);
		mercado.daLance(nomeProduto, "999.999.999", 900.0);
		
		Produto produto = (Produto) mercado.getProdutosEmLeilao().get(0);
		
		List<Lance> lances = produto.lancesEfetuadosPorUmUsuario("999.999.999");
		
		assertEquals(1, lances.size());
		
	}
	
	@Test
	public void testeSemLances() throws Exception{
		nomeUsuario = "Joao";
		enderecoUsuario = "Centro";
		emailUsuario = "joao@com";
		cpfLeiloadorProduto = "111.111.111";
		
		mercado.cadastrarUsuario(nomeUsuario, enderecoUsuario, emailUsuario, cpfLeiloadorProduto);
		mercado.cadastrarProduto(nomeProduto, descricaoProduto, valorMinimoProduto, cpfLeiloadorProduto, dataLimiteProduto);
		
		Produto produto = (Produto) mercado.getProdutosEmLeilao().get(0);
		
		List<Lance> lances = produto.lancesEfetuadosPorUmUsuario("999.999.999");
		
		assertEquals(0, lances.size());
		
	}
	
	@Test
	public void testeLanceUsuarioErrado() throws Exception{
		mercado.cadastrarUsuario("Joao", "Centro", "joao@com", "999.999.999");
		mercado.cadastrarUsuario("Maria", "Centro", "Maria@com", "777.777.777");
		mercado.cadastrarUsuario("Jose", "Centro", "jose@com", "888.888.888");
		mercado.cadastrarProduto(nomeProduto, descricaoProduto, valorMinimoProduto, "999.999.999", dataLimiteProduto);
		mercado.daLance(nomeProduto, "777.777.777", 1001.0);
		
		Produto produto = (Produto) mercado.getProdutosEmLeilao().get(0);
		
		List<Lance> lances = produto.lancesEfetuadosPorUmUsuario("888.888.888");
		
		assertEquals(0, lances.size());
	}
	
	@Test
	public void testeDoisDistintos() throws Exception{
		mercado.cadastrarUsuario("Joao", "Centro", "joao@com", "999.999.999");
		mercado.cadastrarUsuario("Maria", "Centro", "Maria@com", "777.777.777");
		mercado.cadastrarUsuario("Jose", "Centro", "jose@com", "888.888.888");
		mercado.cadastrarProduto(nomeProduto, descricaoProduto, valorMinimoProduto, "999.999.999", dataLimiteProduto);
		mercado.daLance(nomeProduto, "777.777.777", 1001.0);
		mercado.daLance(nomeProduto, "888.888.888", 1005.0);
		
		Produto produto = (Produto) mercado.getProdutosEmLeilao().get(0);
		
		List<Lance> lances = produto.lancesEfetuadosPorUmUsuario("888.888.888");
		
		assertEquals(1, lances.size());

	}
	
	@Test
	public void testeDoisLances() throws Exception{
		mercado.cadastrarUsuario("Joao", "Centro", "joao@com", "999.999.999");
		mercado.cadastrarUsuario("Maria", "Centro", "Maria@com", "777.777.777");
		mercado.cadastrarProduto(nomeProduto, descricaoProduto, valorMinimoProduto, "999.999.999", dataLimiteProduto);
		mercado.daLance(nomeProduto, "777.777.777", 1001.0);
		mercado.daLance(nomeProduto, "777.777.777", 1005.0);
		
		Produto produto = (Produto) mercado.getProdutosEmLeilao().get(0);
		
		List<Lance> lances = produto.lancesEfetuadosPorUmUsuario("777.777.777");
		
		assertEquals(2, lances.size());
	}

}
