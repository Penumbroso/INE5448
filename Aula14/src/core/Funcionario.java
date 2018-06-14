package core;

import java.util.ArrayList;

public class Funcionario {
	private ArrayList<Ocorrencia> ocorrenciasAbertas;
	private String nome;
	
	public Funcionario(String nome){
		ocorrenciasAbertas = new ArrayList<Ocorrencia>();
		this.nome = nome;
	}

	public boolean adicionarOcorrencia(Ocorrencia ocorrencia) {
		if(this.ocorrenciasAbertas.size() < 10){
			this.ocorrenciasAbertas.add(ocorrencia);
			return true;
		}
		return false;
	}
	
	public boolean estaTrabalhandoNaOcorrencia(Ocorrencia ocorrencia){
		if(this.ocorrenciasAbertas.contains(ocorrencia)){
			return true;
		}
		return false;
	}

	public ArrayList<Ocorrencia> getOcorrencias() {
		return ocorrenciasAbertas;
	}

	public void removerOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrenciasAbertas.remove(ocorrencia);
		
	}
}
