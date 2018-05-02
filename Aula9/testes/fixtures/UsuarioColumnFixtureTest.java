package fixtures;

import fit.ColumnFixture;
import fitlibrary.ArrayFixture;
import modelo.*;

public class UsuarioColumnFixtureTest extends ColumnFixture{
	public String nome;
	public String endereco;
	public String email;
	public String cpf;
	
	FachadaMercadoLeilaoComSerializacao fachada = new FachadaMercadoLeilaoComSerializacao();
	
	public UsuarioColumnFixtureTest(){
		super();
	}

	public boolean cadastrarUsuario(){
		try{
			fachada.cadastrarUsuario(this.nome, this.endereco, this.email, this.cpf);
		} catch(Exception e){
			return false;
		}
		return true;
	}
	
	public boolean verificarUsuarioExiste(){
		try{
			fachada.getUsuarioPor(this.cpf);
		} catch(Exception e){
			return false;
		}
		return true;
	}
}
