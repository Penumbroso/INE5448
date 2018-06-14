package core;

import java.util.ArrayList;

public class Empresa {
	ArrayList<Funcionario> funcionarios;
	ArrayList<Projeto> projetos;
	
	public Empresa(){
		funcionarios = new ArrayList<Funcionario>();
		projetos = new ArrayList<Projeto>();
	}

	public ArrayList<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	
	public ArrayList<Projeto> getProjetos() {
		return projetos;
	}

	public boolean criarProjeto() {
		Projeto projeto = new Projeto();
		projetos.add(projeto);
		return true;
	}
	
	public boolean adicionarOcorrencia(Projeto projeto, Ocorrencia ocorrencia){
		if(this.funcionarios.contains(ocorrencia.getResponsavel()) && this.projetos.contains(projeto)){
			projeto.adicionarOcorrencia(ocorrencia);
			return true;
		}
		return false;
	}
	
	public int getNumeroDeProjetos(){
		return projetos.size();
	}
	
	public int getNumeroDeFuncionarios(){
		return funcionarios.size();
	}

	public boolean adicionarFuncionario(Funcionario funcionario) {
		if(!this.funcionarios.contains(funcionario)){
			this.funcionarios.add(funcionario);
			return true;
		}
		return false;
	}

	public boolean adicionarProjeto(Projeto projeto) {
		if(!projetos.contains(projeto)){
			projetos.add(projeto);
			return true;
		}
		return false;
		
	}
	
	public boolean contemProjeto(Projeto projeto){
		if(this.projetos.contains(projeto))
			return true;
		return false;
	}

}
