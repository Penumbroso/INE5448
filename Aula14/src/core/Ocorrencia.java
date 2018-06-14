package core;

import java.util.UUID;

public class Ocorrencia {
	private String chaveUnica;
	private TipoOcorrencia tipo;
	private PrioridadeOcorrencia prioridade;
	private EstadoOcorrencia estado;
	private Funcionario responsavel;
	private String resumo;
	
	public Ocorrencia(Funcionario responsavel){
		this.estado = EstadoOcorrencia.ABERTA;
		this.responsavel = responsavel;
		this.chaveUnica = UUID.randomUUID().toString();
	}
	
	public Funcionario getResponsavel() {
		return this.responsavel;
	}
	
	public String getChaveUnica(){
		return this.chaveUnica;
	}

	public boolean isConcluida() {
		if(estado == EstadoOcorrencia.ABERTA)
			return false;
		return true;
	}

	public TipoOcorrencia getTipoOcorrencia() {
		return tipo;
	}

	public void setTipo(TipoOcorrencia tipo) {
		this.tipo = tipo;
		
	}

	public boolean setPrioridade(PrioridadeOcorrencia prioridade) {
		if(this.estado == EstadoOcorrencia.ABERTA){
			this.prioridade = prioridade;
			return true;
		}
		return false;
	}
	
	public boolean setResponsavel(Funcionario funcionario){
		if(this.estado == EstadoOcorrencia.ABERTA){
			this.responsavel = funcionario;
			return true;
		}
		return false;	
	}

	public PrioridadeOcorrencia getPrioridade() {
		return prioridade;
	}

	public void setEstado(EstadoOcorrencia estado) {
		this.estado = estado;
		
	}

	public EstadoOcorrencia getEstado() {
		return this.estado;
	}

}
