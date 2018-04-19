package modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class TesteMercado {
	private MercadoLeilao mercado;
	private String nomeVendedor;
	private String emailVendedor;
	private String cpfVendedor;
	private String enderecoVendedor;
	private String nomeProduto;
	private double valorProduto;
	private Date dataLimiteProduto;
	
	@Before
	public void configure(){
		mercado = new MercadoLeilao();
		nomeVendedor = "Joao";
		emailVendedor = "joao@com";
		cpfVendedor = "999.999.999";
		enderecoVendedor = "Centro";
		nomeProduto = "Panela";
		valorProduto = 300.0;
		dataLimiteProduto = new Date(new Date().getTime() + 3000);
	}
	
	@Test
	public void testeCadastrarUsuario() throws Exception{
		Usuario usuario = new Usuario("999.999.999", "Joao");
		usuario.setEndereco("Centro");
		usuario.setEmail("joao@com");

		mercado.cadastrarUsuario(usuario.getNome(), usuario.getEndereco(), usuario.getEmail(), usuario.getCpf());
		Usuario result = (Usuario) mercado.getUsuariosCadastrados().get(0);

		assertEquals(usuario.getNome(), result.getNome());
		assertEquals(usuario.getCpf(), result.getCpf());
		assertEquals(usuario.getEmail(), result.getEmail());
		assertEquals(usuario.getEndereco(), "Centro");

	}
	
	@Test
	public void testeDaLance() throws Exception{
		Usuario vendedor = new Usuario("999.999.999", "Joao");
		Usuario comprador = new Usuario("888.888.888", "Maria");
		Produto produto = new Produto(nomeProduto, "", valorProduto, vendedor);
		
		mercado.cadastrarUsuario(vendedor.getNome(), "Centro", "joao@com", vendedor.getCpf());
		mercado.cadastrarUsuario(comprador.getNome(), "Centro", "maria@com", comprador.getCpf());
		
		mercado.cadastrarProduto(produto.getNome(), produto.getDescricao(), 
				produto.getLanceMinimo(), vendedor.getCpf(), dataLimiteProduto);
		
		mercado.daLance(nomeProduto, comprador.getCpf(), 305.0);
		
		assertTrue(mercado.retornaTodosOsLancesEfetuados().size() > 0);
		assertTrue(mercado.retornaLancesDeUmUsuario(comprador.getCpf()).size() > 0);
		assertEquals(mercado.getProdutosQueDeuLance(comprador.getCpf()).get(0).getNome(), produto.getNome());
		assertEquals(mercado.retornaTodosOsLancesEfetuados().get(0).getCpfDonoDoLance(), "888.888.888");
		assertEquals(mercado.retornaTodosOsLancesEfetuados().get(0).getProdutoQueRecebeuOLance().getNome(), produto.getNome());
		assertEquals(Math.abs(mercado.retornaTodosOsLancesEfetuados().get(0).getValorDoLance().intValue()), Math.abs(305));
		
	}
	
	@Test (expected = Exception.class)
	public void testeCadastrarMesmoUsuario() throws Exception{
		Usuario usuario = new Usuario("999.999.999", "Joao");
		usuario.setEndereco("Centro");
		usuario.setEmail("joao@com");
		
		mercado.cadastrarUsuario(usuario.getNome(), usuario.getEndereco(), usuario.getEmail(), usuario.getCpf());
		mercado.cadastrarUsuario(usuario.getNome(), usuario.getEndereco(), usuario.getEmail(), usuario.getCpf());
	}
	
	@Test
	public void testeCadastrarProduto() throws Exception{
		Usuario usuario = new Usuario("999.999.999", "Joao");
		Produto produto = new Produto(nomeProduto, "", valorProduto, usuario);
		
		mercado.cadastrarUsuario(usuario.getNome(), "Centro", "joao@com", usuario.getCpf());

		mercado.cadastrarProduto(produto.getNome(), produto.getDescricao(), 
				produto.getLanceMinimo(), usuario.getCpf(), dataLimiteProduto);
		
		assertTrue(mercado.getProdutosEmLeilao().size() > 0);
		assertEquals(usuario.toString(), mercado.getUsuariosCadastrados().get(0).toString());
		assertEquals(mercado.retornaProdutosDeUmLeiloador(usuario.getCpf()).size(), 1);
	}
	
	@Test(expected=Exception.class)
	public void testeDaLanceProdutoJaVendido() throws Exception{
		mercado.cadastrarUsuario("CesarComprador1", "Rua tal", "cc1@gmail.com", "999.999.999");
		mercado.cadastrarUsuario("CesarComprador2", "Rua tal", "cc1@gmail.com", "999.999.997");
		mercado.cadastrarUsuario("CesarVendedor", "Rua tal tal", "cv@gmail.com", "999.999.998");
		mercado.cadastrarProduto("casa", "3 quartos", 20000.0, "999.999.998", new Date(new Date().getTime() + 1000));
		mercado.daLance("casa", "999.999.999", 21000.0);
		Thread.sleep(2000);
		mercado.daLance("casa", "999.999.997", 22000.0);
	}
	
	@Test(expected=Exception.class)
	public void testeDaLanceCompradorInexistente() throws Exception{
		mercado.cadastrarUsuario("CesarVendedor", "Rua tal tal", "cv@gmail.com", "999.999.998");
		mercado.cadastrarProduto("casa", "3 quartos", 20000.0, "999.999.998", new Date(new Date().getTime() + 3000));
		mercado.daLance("casa", "999.999.999", 20100.0);
		
	}
	
	@Test(expected=Exception.class)
	public void testeDaLanceProdutoInexistente() throws Exception{
		mercado.daLance("casa", "999.999.999", 20100.0);
		
	}
	
	@Test(expected=Exception.class)
	public void testeDaLanceProdutoExpirado() throws Exception{
		mercado.cadastrarUsuario("CesarComprador", "Rua tal", "cc@gmail.com", "999.999.999");
		mercado.cadastrarUsuario("CesarVendedor", "Rua tal tal", "cv@gmail.com", "999.999.998");
		mercado.cadastrarProduto("casa", "3 quartos", 20000.0, "999.999.998", new Date(new Date().getTime() - 3000));
		mercado.daLance("casa", "999.999.999", 21000.0);
	}
	
	
	

}
