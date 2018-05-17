package br.ufsc.cucumber.sr05.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Before;

import br.ufsc.data.SupplierManagement;
import br.ufsc.data.service.SupplierManagementService;
import br.ufsc.entity.ExcellenceCertificate;
import br.ufsc.entity.Supplier;
import br.ufsc.junit.*;

public class CancelExcellenceCertifacateTest {
	
	SupplierManagementService smS = new SupplierManagementService();
	Supplier fornecedor;
	ExcellenceCertificate atestadoDeCapacidadeTecnica;
	
	@Before
	public void setUp(){
		SupplierManagement sm;
		try {
			//Apaga todos os dados
			sm = new SupplierManagement();
			smS.save(sm);
			
			//Inicializa todos os dados
			new Person_setup().insert();
			new Product_setup().insert();
			new Sanction_setup().insert();
			new Supplier_setup().insert();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Given("^Dado o fornecedor com razao social \"([^\"]*)\"$")
	public void dado_o_fornecedor_com_razao_social(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		List<Supplier> listSuppliers = Collections.list(smS.getData().getMapOfSupplier().elements());
		for (Supplier supplier : listSuppliers){
			String razaoSocial = supplier.getLegalName();
			if(arg1.contains(razaoSocial)){
				fornecedor = supplier;
			}
			
		}
		Assert.assertNotNull(fornecedor);
	}

	@Given("^o numero do documento \\(CNPJ\\) \"([^\"]*)\"$")
	public void o_numero_do_documento_CNPJ(String arg1) throws Throwable {
	    String cnpj = fornecedor.getDocumentNumber();
	    Assert.assertEquals(arg1, cnpj);
	}

	@Given("^o fornecedor possui um atestado de capacidade tecnica ativo$")
	public void o_fornecedor_possui_um_atestado_de_capacidade_tecnica_ativo() throws Throwable {
		List<ExcellenceCertificate> listExcellenceCertificate =  Collections.list(fornecedor.getMapOfExcellenceCertificate().elements());
		
		for(ExcellenceCertificate excellenceCertificate : listExcellenceCertificate){
			atestadoDeCapacidadeTecnica = excellenceCertificate;
		}
		
		Assert.assertNotNull(atestadoDeCapacidadeTecnica);
	}

	@Given("^o atestado de capacidade tecnica esta suspenso por mais de (\\d+) dias$")
	public void o_atestado_de_capacidade_tecnica_esta_suspenso_por_mais_de_dias(int arg1) throws Throwable {
		Date dataDeSuspencao = Calendar.getInstance().getTime();;
		Date hoje = Calendar.getInstance().getTime();
		dataDeSuspencao = atestadoDeCapacidadeTecnica.getLastSuspentionDate();
		
		long startTime = dataDeSuspencao.getTime();
		long endTime = hoje.getTime();
		long diffTime = endTime - startTime;
		long diffDays = diffTime / (1000 * 60 * 60 * 24);
	
	    Assert.assertTrue(diffDays > arg1);
		
	}

	@Given("^o atestado de capacidade tecnica nao esta cancelado$")
	public void o_atestado_de_capacidade_tecnica_nao_esta_cancelado() throws Throwable {
		boolean isCancelado = atestadoDeCapacidadeTecnica.getIsCanceled();
		
	    Assert.assertFalse(isCancelado);
	}

	@When("^O usuario logar no sistema$")
	public void o_usuario_logar_no_sistema() throws Throwable {
	    Assert.assertTrue(true);
	}

	@Then("^o sistema deve alterar a situacao do atestado de capacidade tecnica para cancelado$")
	public void o_sistema_deve_alterar_a_situacao_do_atestado_de_capacidade_tecnica_para_cancelado() throws Throwable {
		atestadoDeCapacidadeTecnica.setIsCanceled(true);
		Assert.assertTrue(atestadoDeCapacidadeTecnica.getIsCanceled());
	}

	@Then("^alterar a data de cancelamento do atestado de capacidade tecnica para a data atual$")
	public void alterar_a_data_de_cancelamento_do_atestado_de_capacidade_tecnica_para_a_data_atual() throws Throwable {
		Date hoje = Calendar.getInstance().getTime();
	    atestadoDeCapacidadeTecnica.setCancelationDate(hoje);
	    Assert.assertEquals(hoje, atestadoDeCapacidadeTecnica.getCancelationDate());
	}

	@Then("^emitir um alerta de que o atestado de capacidade tecnica foi cancelado$")
	public void emitir_um_alerta_de_que_o_atestado_de_capacidade_tecnica_foi_cancelado() throws Throwable {
		Assert.assertTrue(atestadoDeCapacidadeTecnica.getIsCanceled());
	}

}
