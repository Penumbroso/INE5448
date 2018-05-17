package br.ufsc.fit.fixtures;

import java.util.Collections;
import java.util.List;

import br.ufsc.data.SupplierManagement;
import br.ufsc.data.service.SupplierManagementService;
import br.ufsc.entity.ExcellenceCertificate;
import br.ufsc.entity.Supplier;
import br.ufsc.junit.Person_setup;
import br.ufsc.junit.Product_setup;
import br.ufsc.junit.Sanction_setup;
import br.ufsc.junit.Supplier_setup;
import fit.ColumnFixture;

public class SuspendOrCancelExcellenceCertificateDueSanctionColumnFixture extends ColumnFixture{
	public String nomeDoFornecedor;
	public String documento;
	public String sancao;
	public String dataInicio;
	public String dataFim;
	public String justificativa;
	
	private ExcellenceCertificate certificado;
	
	SupplierManagementService smS = new SupplierManagementService();
	
	public void setup(){
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
	
	public String atestadoDeCapacidadeTecnica(){
		List<Supplier> listSuppliers = Collections.list(smS.getData().getMapOfSupplier().elements());
		for (Supplier supplier : listSuppliers){
			if(supplier.getDocumentNumber().equals(documento) && supplier.getSocialName().equals(nomeDoFornecedor)){
				List<ExcellenceCertificate> listExcellenceCertificate =  Collections.list(supplier.getMapOfExcellenceCertificate().elements());
				for(ExcellenceCertificate excellenceCertificate : listExcellenceCertificate){
					certificado = excellenceCertificate;
				}
			}
		}
		return "";
	}
	
	public String mensagem(){
		return "";
	}
}
