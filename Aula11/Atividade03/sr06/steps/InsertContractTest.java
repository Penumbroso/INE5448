package br.ufsc.cucumber.sr06.steps;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;

import br.ufsc.data.SupplierManagement;
import br.ufsc.data.service.SupplierManagementService;
import br.ufsc.entity.Contract;
import br.ufsc.entity.Sanction;
import br.ufsc.entity.SanctionApplied;
import br.ufsc.entity.Supplier;
import br.ufsc.entity.service.ContractService;
import br.ufsc.junit.Person_setup;
import br.ufsc.junit.Product_setup;
import br.ufsc.junit.Sanction_setup;
import br.ufsc.junit.Supplier_setup;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class InsertContractTest {

	SupplierManagementService smS = new SupplierManagementService();
	ContractService cs = new ContractService();
	Supplier fornecedor;
	private String numeroDoContrato;
	private String dataDeAssinaturaDoContrato;
	private String dataDeValidadeDoContrato;
	private String valorTotalDoContrato;
	
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
	
	@Given("^Dado o fornecedor com a razao social \"([^\"]*)\"$")
	public void dado_o_fornecedor_com_a_razao_social(String arg1) throws Throwable {
		List<Supplier> listSuppliers = Collections.list(smS.getData().getMapOfSupplier().elements());
		
		for (Supplier supplier : listSuppliers){
			if(supplier.getSocialName().equals(arg1)){
				fornecedor = supplier;
			}
		}
		
		Assert.assertNotNull(fornecedor);
	}

	@Given("^o CNPJ \\(Documento\\) \"([^\"]*)\"$")
	public void o_CNPJ_Documento(String arg1) throws Throwable {
	    String cnpj = fornecedor.getDocumentNumber();
	    Assert.assertEquals(cnpj, arg1);
	}

	@Given("^o fornecedor nao possui sancao ativa$")
	public void o_fornecedor_nao_possui_sancao_ativa() throws Throwable {
		int numeroDeSancoes = 0;
		List<Contract> listContract = Collections.list(fornecedor.getMapOfContracts().elements());
		for(Contract contract : listContract){
			List<SanctionApplied> listSanctionApplied = Collections.list(contract.getMapOfSanctionApplied().elements());
			numeroDeSancoes = listSanctionApplied.size();
		}
		Assert.assertEquals(0, numeroDeSancoes);
	}

	@When("^O usuario informar o numero do contrato \"([^\"]*)\"$")
	public void o_usuario_informar_o_numero_do_contrato(String arg1) throws Throwable {
	    numeroDoContrato = arg1;
	}

	@When("^a data de assinatura do contrato \"([^\"]*)\"$")
	public void a_data_de_assinatura_do_contrato(String arg1) throws Throwable {
	    dataDeAssinaturaDoContrato = arg1;
	}

	@When("^a data de validade do contrato \"([^\"]*)\"$")
	public void a_data_de_validade_do_contrato(String arg1) throws Throwable {
	    dataDeValidadeDoContrato = arg1;
	}

	@When("^o valor total do contrato \"([^\"]*)\"$")
	public void o_valor_total_do_contrato(String arg1) throws Throwable {
	    valorTotalDoContrato = arg1;
	}

	@Then("^o sistema emite uma mensagem que o contrato foi cadastrado com sucesso$")
	public void o_sistema_emite_uma_mensagem_que_o_contrato_foi_cadastrado_com_sucesso() throws Throwable {
		SupplierManagement sm = smS.getData();
		Contract contrato = new Contract();
		contrato.setContractNumber(numeroDoContrato);
		contrato.setContractDate(new SimpleDateFormat("dd/MM/yyyy").parse(dataDeAssinaturaDoContrato));
		contrato.setExpirationDate(new SimpleDateFormat("dd/MM/yyyy").parse(dataDeValidadeDoContrato));
		contrato.setContractValue(Double.parseDouble(valorTotalDoContrato));
		contrato.setId(sm.getIdentitySequence().getNext());
		
		Contract resultante = cs.insert(fornecedor, contrato);
		
		
	}

	@Given("^o fornecedor possui no contrato \"([^\"]*)\" a sancao \"([^\"]*)\"$")
	public void o_fornecedor_possui_no_contrato_a_sancao(String arg1, String arg2) throws Throwable {
		System.out.println(fornecedor.getSocialName());
		boolean possuiSancao = false;
		List<Contract> listContract = Collections.list(fornecedor.getMapOfContracts().elements());
		System.out.println(listContract.size());
		for(Contract contract : listContract){
			System.out.println(contract.getContractNumber());
			if(contract.getContractNumber().equals(arg1)){
				List<SanctionApplied> listSanctionApplied = Collections.list(contract.getMapOfSanctionApplied().elements());
				System.out.println(contract.getContractNumber());
				for (SanctionApplied sanctionApplied : listSanctionApplied){
					System.out.println(sanctionApplied.getSanction().getDescription());
					possuiSancao = sanctionApplied.getSanction().getDescription().equals(arg2);
				}
			}
			
		}
		
		Assert.assertTrue(possuiSancao);
	}

	@Then("^emite um alerta que o fornecedor possui uma sancao que nao gera impedimento de contratacao$")
	public void emite_um_alerta_que_o_fornecedor_possui_uma_sancao_que_nao_gera_impedimento_de_contratacao() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^o sistema emite uma mensagem que o contrato nao foi cadastrado$")
	public void o_sistema_emite_uma_mensagem_que_o_contrato_nao_foi_cadastrado() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^emite um alerta que o fornecedor possui uma sancao que gera impedimento de contratacao$")
	public void emite_um_alerta_que_o_fornecedor_possui_uma_sancao_que_gera_impedimento_de_contratacao() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}


}
