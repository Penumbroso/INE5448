package br.ufsc.fit.fixtures;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import org.junit.Before;

import br.ufsc.data.SupplierManagement;
import br.ufsc.data.service.SupplierManagementService;
import br.ufsc.entity.ExcellenceCertificate;
import br.ufsc.entity.Supplier;
import br.ufsc.junit.Person_setup;
import br.ufsc.junit.Product_setup;
import br.ufsc.junit.Sanction_setup;
import br.ufsc.junit.Supplier_setup;
import fit.ColumnFixture;

public class IssueExcelenceCertificateColumnFixture extends ColumnFixture{
	public String nomeDoFornecedor;
	public String documento;
	public String dataDeValidade;
	
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
	
	public String mensagemDeRetorno() throws ParseException{
		setup();
		
		List<Supplier> listSuppliers = Collections.list(smS.getData().getMapOfSupplier().elements());
		for (Supplier supplier : listSuppliers){
			if(supplier.getDocumentNumber().equals(documento) && supplier.getSocialName().equals(nomeDoFornecedor)){
				List<ExcellenceCertificate> listExcellenceCertificate =  Collections.list(supplier.getMapOfExcellenceCertificate().elements());
				for(ExcellenceCertificate excellenceCertificate : listExcellenceCertificate){
					certificado = excellenceCertificate;
				}
			}
		}
		
		
		if(certificado.getIsCanceled() && certificado != null){
			return "Nao e possivel emitir o atestado de capacidade tecnica, pois o fornecedor possui um atestado cancelado no ultimos 36 meses.";
		}else
		
		if(certificado.getIsSuspended() && certificado != null){
			return "Nao e possivel emitir o atestado de capacidade tecnica, pois o fornecedor possui um atestado suspenso no ultimos 36 meses.";
		}else
			return "Certificado de atestado de capacidade tecnica emitido com sucesso!";
		
	}
	
}