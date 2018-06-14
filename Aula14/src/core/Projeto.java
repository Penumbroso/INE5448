package core;

import java.util.ArrayList;

public class Projeto {
	ArrayList<Ocorrencia> ocorrencias;
	
	public Projeto(){
		ocorrencias = new ArrayList<Ocorrencia>();
	}

	public ArrayList<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}

	public boolean terminarOcorrencia(Ocorrencia ocorrencia) {
		if(ocorrencias.contains(ocorrencia) && !ocorrencia.isConcluida()){
			ocorrencia.setEstado(EstadoOcorrencia.FECHADA);
			ocorrencia.getResponsavel().removerOcorrencia(ocorrencia);
			return true;
		}
		return false;
	}

	public boolean adicionarOcorrencia(Ocorrencia ocorrencia) {
		Funcionario responsavel = ocorrencia.getResponsavel();
		if(responsavel.getOcorrencias().size() < 10){
			ocorrencias.add(ocorrencia);
			responsavel.adicionarOcorrencia(ocorrencia);
			return true;
		}
		return false;
		
	}
}
