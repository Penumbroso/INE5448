package fixtures;

import fit.ActionFixture;
import modelo.*;

public class MercadoActionFixtureTest extends ActionFixture{
	FachadaMercadoLeilaoComSerializacao fachada = new FachadaMercadoLeilaoComSerializacao();
	
	public void montarMercado(){
		fachada.montarMercado();
	}
	
	public boolean mercadoMontado(){
		return fachada.isMercadoMontado();
	}
	
	public void desmontarMercado(){
		fachada.desmontarMercado();
	}
	
	public boolean mercadoDesmontado(){
		return fachada.isMercadoDesmontado();
	}

	public void limparMercado(){
		fachada.limparMercado();
	}
	
}
