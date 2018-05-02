package fixtures;

import java.util.Date;

import fit.ColumnFixture;
import modelo.FachadaMercadoLeilaoComSerializacao;

public class ProdutoColumnFixtureTest extends ColumnFixture {

	public String nome;
	public String descricao;
	public Double lanceMinimo;
	public String cpfLeiloador;
	public Date dataLimite;
	
	FachadaMercadoLeilaoComSerializacao fachada = new FachadaMercadoLeilaoComSerializacao();
	
	public ProdutoColumnFixtureTest(){
		super();
	}
	
	public boolean cadastrarProduto(){
		try{
			fachada.cadastrarProduto(this.nome, this.descricao, this.lanceMinimo, this.cpfLeiloador, this.dataLimite);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public boolean produtoJaCadastrado(){
		try{
			return fachada.verificaSeOProdutoJaExiste(this.nome);
		}catch(Exception e){
			return false;
		}
	}
	
	
}
