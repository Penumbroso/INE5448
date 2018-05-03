package acceptanceTests.cucumberSteps;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;

import cucumber.api.CucumberOptions;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;
import interfaces.IUsuario;
import modelo.*;

@StepDefAnnotation
@CucumberOptions(glue = "acceptanceTests.cucumberSteps",
	features = "AppLeilao/src/test/java/features/CadastrarProduto_Test.feature",
	monochrome = true)
public class CadastrarProdutoTest {
	public String nome = "";
	public String descricao = "";
	public double lanceMinimo = 0;
	public String cpfLeiloador = "";
	public Date dataLimite;
	public Boolean expectedResult = true;
	
	FachadaMercadoLeilaoComSerializacao fachada = new FachadaMercadoLeilaoComSerializacao();

	@Before
	public void setup(){
		try {
			fachada.cadastrarUsuario("Ricardo", "Campus", "ricardo.almeida@grad.ufsc.br", "840.287.935-72");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Given("^O nome do produto e \"([^\"]*)\"$")
	public void o_nome_do_produto_e(String arg1) throws Throwable {
	    this.nome = arg1;
	}

	@Given("^descricao e \"([^\"]*)\"$")
	public void descricao_e(String arg1) throws Throwable {
	    this.descricao = arg1;
	}

	@Given("^lance minimo e (\\d+)$")
	public void lance_minimo_e(int arg1) throws Throwable {
	    this.lanceMinimo = arg1;
	}

	@Given("^cpf do leiloador e \"([^\"]*)\"$")
	public void cpf_do_leiloador_e(String arg1) throws Throwable {
	    this.cpfLeiloador = arg1;
	}

	@Given("^data limite e (\\d+)-(\\d+)-(\\d+)$")
	public void data_limite_e(int arg1, int arg2, int arg3) throws Throwable {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2020);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		this.dataLimite = cal.getTime();
	}

	@Given("^um usuario ja foi cadastrado$")
	public void um_usuario_ja_foi_cadastrado() throws Throwable {
	    IUsuario user = fachada.getUsuarioPor(this.cpfLeiloador);
	    Assert.assertNotNull(user);
	}
	
	
	@Given("^produto com mesmo \"([^\"]*)\" nao existe$")
	public void produto_com_mesmo_nao_existe(String arg1) throws Throwable {
		boolean produtoExiste = fachada.verificaSeOProdutoJaExiste(arg1);
	    Assert.assertEquals(false, produtoExiste);
	}


	@When("^o produto for cadastrado$")
	public void o_produto_for_cadastrado() throws Throwable {
		fachada.cadastrarProduto(this.nome, this.descricao, this.lanceMinimo, this.cpfLeiloador, this.dataLimite);
	}

	@Then("^o sistema deve cadastrar o produto com sucesso$")
	public void o_sistema_deve_cadastrar_o_produto_com_sucesso() throws Throwable {
	    boolean cadastro = fachada.verificaSeOProdutoJaExiste(this.nome);
	    Assert.assertEquals(this.expectedResult, cadastro);
	}
}
